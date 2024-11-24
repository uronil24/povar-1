package com.example.povar.domain.model

class Recipe(
    val id: Long,
    val name: String,
    val desc: String,
    val category: String,
    val ingredients: List<Ingredient>
)