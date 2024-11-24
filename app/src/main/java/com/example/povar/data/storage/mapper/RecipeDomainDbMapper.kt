package com.example.povar.data.storage.mapper

import com.example.povar.data.storage.model.RecipeDb
import com.example.povar.domain.model.Recipe
import javax.inject.Inject

interface RecipeDomainDbMapper {
    fun toDomain(recipeDb: RecipeDb) : Recipe

    fun toDb(recipe: Recipe) : RecipeDb
}

class RecipeDomainDbMapperImpl @Inject constructor() : RecipeDomainDbMapper{
    override fun toDomain(recipeDb: RecipeDb): Recipe {
        return Recipe(
            id = recipeDb.id ?: 0L,
            name = recipeDb.name ?: "",
            desc = recipeDb.desc ?: "",
            category = recipeDb.category ?: "",
            ingredients = recipeDb.ingredients ?: emptyList()
        )
    }

    override fun toDb(recipe: Recipe): RecipeDb {
        return RecipeDb(
            id = recipe.id,
            name = recipe.name,
            desc = recipe.desc,
            category = recipe.category,
            ingredients = recipe.ingredients
        )
    }

}