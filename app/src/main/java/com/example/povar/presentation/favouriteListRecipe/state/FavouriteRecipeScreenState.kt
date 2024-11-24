package com.example.povar.presentation.favouriteListRecipe.state

import com.example.povar.presentation.model.RecipeItem

interface FavouriteRecipeScreenState {
    val recipes: List<RecipeItem>
}