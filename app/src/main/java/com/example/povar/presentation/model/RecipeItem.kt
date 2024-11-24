package com.example.povar.presentation.model

import com.example.povar.domain.model.Ingredient

class RecipeItem(
    val id: Long,
    val name: String,
    val desc: String,
    val category: String,
    val ingredients: String
)

fun Ingredient.toStr() = "$name - $description"

fun String.toIngredient(): Ingredient{
    val res = this.split(" - ")
    return Ingredient(name = res[0], description = res[1])
}