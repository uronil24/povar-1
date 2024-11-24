package com.example.povar.presentation.listRecipe.state

import com.example.povar.presentation.model.RecipeItem

interface RecipesScreenState {
    val recipes: List<RecipeItem>
    val filter: String
    val isFiltered: Boolean
}