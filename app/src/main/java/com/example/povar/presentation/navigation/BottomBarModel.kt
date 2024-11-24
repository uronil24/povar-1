package com.example.povar.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarModel(
    val route: String,
    val title: String? = null,
    val setIcon: ImageVector? = null,
    val unsetIcon: ImageVector? = null,
){
    data object Recipes : BottomBarModel(
        route = "Recipes",
        title = "Рецепты",
        setIcon = Icons.Filled.Fastfood,
        unsetIcon = Icons.Outlined.Fastfood,
    )

    data object Profile : BottomBarModel(
        route = "Profile",
        title = "Профиль",
        setIcon = Icons.Filled.Person,
        unsetIcon = Icons.Outlined.Person
    )
}