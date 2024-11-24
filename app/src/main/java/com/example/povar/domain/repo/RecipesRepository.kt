package com.example.povar.domain.repo

import com.example.povar.domain.model.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>

    suspend fun getRecipesDao(): List<Recipe>

    suspend fun saveRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)
}
