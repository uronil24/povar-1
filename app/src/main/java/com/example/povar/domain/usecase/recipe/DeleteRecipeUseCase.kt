package com.example.povar.domain.usecase.recipe

import com.example.povar.domain.model.Recipe
import com.example.povar.domain.repo.RecipesRepository
import javax.inject.Inject

interface DeleteRecipeUseCase {
    suspend fun delete(recipe: Recipe)
}

class DeleteRecipeUseCaseImpl @Inject constructor(
    private val repo: RecipesRepository
) : DeleteRecipeUseCase {
    override suspend fun delete(recipe: Recipe) {
        repo.deleteRecipe(recipe = recipe)
    }
}