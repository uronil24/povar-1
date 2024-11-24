package com.example.povar.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModel(
    val route: String,
    val title: String? = null,
){
    data object Favourite: ScreenModel(
        route = "Favourites",
        title = "Избранные",
    )

    data object Edit: ScreenModel(
        route = "Edit",
        title = "Изменить пользователя",
    )
}