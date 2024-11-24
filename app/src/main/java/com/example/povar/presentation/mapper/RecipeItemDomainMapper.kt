package com.example.povar.presentation.mapper

import com.example.povar.domain.model.Recipe
import com.example.povar.presentation.model.RecipeItem
import com.example.povar.presentation.model.toIngredient
import com.example.povar.presentation.model.toStr
import javax.inject.Inject

interface RecipeItemDomainMapper {
    fun toItem(recipe: Recipe): RecipeItem

    fun toDomain(recipeItem: RecipeItem): Recipe
}

class RecipeItemDomainMapperImpl @Inject constructor() : RecipeItemDomainMapper {
    override fun toItem(recipe: Recipe): RecipeItem {
        return RecipeItem(
            id = recipe.id,
            name = if (recipe.name.isEmpty()) "Нет названия" else "Название: ${recipe.name}",
            desc = if (recipe.desc.isEmpty()) "Нет описания" else "Описание: ${recipe.desc}",
            category = if (recipe.category.isEmpty()) "Нет категории" else "Категория: ${recipe.category}",
            ingredients = if (recipe.ingredients.isEmpty()) "Нет ингредиентов" else "Ингредиенты:\n${
                recipe.ingredients.joinToString(separator = "\n") { it.toStr() }
            }"
        )
    }

    override fun toDomain(recipeItem: RecipeItem): Recipe {
        return Recipe(
            id = recipeItem.id,
            name = if (recipeItem.name == "Нет названия") "" else recipeItem.name.replace(
                "Название: ",
                ""
            ),
            desc = if (recipeItem.desc == "Нет описания") "" else recipeItem.desc.replace(
                "Описание: ",
                ""
            ),
            category = if (recipeItem.category == "Нет категории") "" else recipeItem.category.replace(
                "Категория: ",
                ""
            ),
            ingredients = if (recipeItem.ingredients == "Нет ингредиентов") emptyList() else recipeItem.ingredients.replace(
                "Ингредиенты:\n",
                ""
            ).split(
                "\n"
            ).map { it.toIngredient() }
        )
    }
}