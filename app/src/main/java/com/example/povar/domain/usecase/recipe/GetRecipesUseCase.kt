package com.example.povar.domain.usecase.recipe

import com.example.povar.domain.model.Recipe
import com.example.povar.domain.repo.RecipesRepository
import javax.inject.Inject

interface GetRecipesUseCase {
    suspend fun getRecipes() : List<Recipe>

    suspend fun getRecipesDao() : List<Recipe>
}

class GetRecipesUseCaseImpl @Inject constructor(
    private val repo: RecipesRepository
) : GetRecipesUseCase {
    override suspend fun getRecipes(): List<Recipe> {
        return repo.getRecipes()
    }

    override suspend fun getRecipesDao(): List<Recipe> {
        return repo.getRecipesDao()
    }
}