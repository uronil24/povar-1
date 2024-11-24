package com.example.povar.presentation.listRecipe.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.povar.domain.usecase.preference.GetPreferencesUseCase
import com.example.povar.domain.usecase.preference.SetPreferencesUseCase
import com.example.povar.domain.usecase.recipe.GetRecipesUseCase
import com.example.povar.domain.usecase.recipe.SaveRecipeUseCase
import com.example.povar.presentation.listRecipe.state.RecipesScreenState
import com.example.povar.presentation.mapper.RecipeItemDomainMapper
import com.example.povar.presentation.model.RecipeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesScreenViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val setPreferencesUseCase: SetPreferencesUseCase,
    private val getPreferencesUseCase: GetPreferencesUseCase,
    private val mapper: RecipeItemDomainMapper
) : ViewModel() {
    private val initialRecipes = mutableListOf<RecipeItem>()

    private val _state = MutableRecipesScreenState()
    val state = _state as RecipesScreenState

    init {
        load()
        toggleFilter(_state.filter, true)
    }

    fun save(recipe: RecipeItem){
        viewModelScope.launch {
            saveRecipeUseCase.saveRecipe(mapper.toDomain(recipe))
        }
    }

    fun toggleFilter(filter: String, isChecked: Boolean) {
        if (!isChecked && _state.filter == filter) {
            sort("")
        } else {
            sort(filter)
        }
    }

    private fun sort(input: String){
        _state.recipes = initialRecipes
        _state.filter = input
        _state.recipes = _state.recipes.filter { it.category.contains(input) }
        viewModelScope.launch {
            setPreferencesUseCase.set(input)
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.recipes = getRecipesUseCase.getRecipes().map { recipe ->
                mapper.toItem(recipe)
            }
            initialRecipes.addAll(_state.recipes)
            getPreferencesUseCase.get().collect {
                _state.filter = it
            }
//            _state.isFiltered = _state.filter.isEmpty()
        }
    }

    private class MutableRecipesScreenState : RecipesScreenState {
        override var recipes: List<RecipeItem> by mutableStateOf(emptyList())
        override var filter: String by mutableStateOf("")
        override var isFiltered: Boolean by mutableStateOf(false)
    }
}