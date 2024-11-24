package com.example.povar.data.storage.repo

import com.example.povar.data.storage.dao.RecipeDao
import com.example.povar.data.storage.mapper.RecipeDomainDbMapper
import com.example.povar.data.storage.mock.MockData
import com.example.povar.data.storage.model.RecipeDb
import com.example.povar.domain.model.Ingredient
import com.example.povar.domain.model.Recipe
import com.example.povar.domain.repo.RecipesRepository
import java.util.UUID
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val dao: RecipeDao,
    private val mapper: RecipeDomainDbMapper
) : RecipesRepository {
    private val mock = MockData().mock

    override suspend fun getRecipes(): List<Recipe> {
        return mock.map { recipeDb ->
            mapper.toDomain(recipeDb)
        }
    }

    override suspend fun getRecipesDao(): List<Recipe> {
        return dao.getRecipes().map { recipe ->
            mapper.toDomain(recipe)
        }
    }

    override suspend fun saveRecipe(recipe: Recipe) {
        dao.saveRecipe(mapper.toDb(recipe))
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe.id)
    }
}