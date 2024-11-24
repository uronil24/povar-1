package com.example.povar.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.povar.presentation.editProfile.EditProfileScreen
import com.example.povar.presentation.favouriteListRecipe.screen.FavouriteRecipesScreen
import com.example.povar.presentation.listRecipe.screen.RecipesScreen
import com.example.povar.presentation.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarModel.Recipes.route
    ) {
        composable(route = BottomBarModel.Recipes.route) {
            RecipesScreen(
                onToFavourite = { navController.navigate(ScreenModel.Favourite.route) }
            )
        }
        composable(route = BottomBarModel.Profile.route) {
            ProfileScreen(
                {navController.navigate(ScreenModel.Edit.route)}
            )
        }
        composable(route = ScreenModel.Edit.route){
            EditProfileScreen { navController.popBackStack() }
        }
        composable(route = ScreenModel.Favourite.route) {
            FavouriteRecipesScreen(onToBack = { navController.popBackStack() })
        }
    }
}