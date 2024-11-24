package com.example.povar.data.storage.mock

import com.example.povar.data.storage.model.RecipeDb
import com.example.povar.domain.model.Ingredient

class MockData {
    val mock: List<RecipeDb> = listOf(
        RecipeDb(
            id = 1,
            name = "Овощной салат",
            desc = "Овощной салат из овощей",
            category = "Салат",
            ingredients = listOf(
                Ingredient(name = "огурцы", description = "1 шт."),
                Ingredient(name = "помидоры", description = "1 шт.")
            )
        ),
        RecipeDb(
            id = 2,
            name = "Яичница",
            desc = "Яичница из яиц",
            category = "Завтрак",
            ingredients = listOf(
                Ingredient(name = "яйцо", description = "3 шт."),
                Ingredient(name = "соль", description = "1 ст. л.")
            )
        )
    )
}