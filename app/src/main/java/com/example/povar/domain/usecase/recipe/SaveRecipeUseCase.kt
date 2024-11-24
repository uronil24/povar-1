package com.example.povar.domain.usecase.recipe

import com.example.povar.domain.model.Recipe
import com.example.povar.domain.repo.RecipesRepository
import javax.inject.Inject

interface SaveRecipeUseCase {
    suspend fun saveRecipe(recipe: Recipe)
}

class SaveRecipeUseCaseImpl @Inject constructor(
    private val repo: RecipesRepository
) : SaveRecipeUseCase {
    override suspend fun saveRecipe(recipe: Recipe) {
        return repo.saveRecipe(recipe = recipe)
    }
}