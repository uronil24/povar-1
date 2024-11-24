package com.example.povar.presentation.favouriteListRecipe.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.povar.domain.usecase.recipe.DeleteRecipeUseCase
import com.example.povar.domain.usecase.recipe.GetRecipesUseCase
import com.example.povar.presentation.favouriteListRecipe.state.FavouriteRecipeScreenState
import com.example.povar.presentation.listRecipe.state.RecipesScreenState
import com.example.povar.presentation.mapper.RecipeItemDomainMapper
import com.example.povar.presentation.model.RecipeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteRecipesScreenViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val mapper: RecipeItemDomainMapper
) : ViewModel() {
    private val _state = MutableRecipesScreenState()
    val state = _state as FavouriteRecipeScreenState

    init {
        load()
    }

    fun delete(recipe: RecipeItem){
        viewModelScope.launch {
            deleteRecipeUseCase.delete(mapper.toDomain(recipe))
            _state.recipes = state.recipes.filter { it != recipe }
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.recipes = getRecipesUseCase.getRecipesDao().map { recipe ->
                mapper.toItem(recipe)
            }
        }
    }

    private class MutableRecipesScreenState : FavouriteRecipeScreenState {
        override var recipes: List<RecipeItem> by mutableStateOf(emptyList())
    }
}