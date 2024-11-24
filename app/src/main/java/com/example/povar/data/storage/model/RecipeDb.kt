package com.example.povar.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.povar.domain.model.Ingredient

@Entity(tableName = "recipes")
data class RecipeDb(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String?,
    val desc: String?,
    val category: String?,
    val ingredients: List<Ingredient>?
)
