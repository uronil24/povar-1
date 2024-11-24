@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.povar.presentation.favouriteListRecipe.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.povar.presentation.favouriteListRecipe.viewModel.FavouriteRecipesScreenViewModel
import com.example.povar.presentation.listRecipe.screen.RecipeCard
import com.example.povar.presentation.navigation.BottomBarModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteRecipesScreen(
    onToBack: () -> Unit,
    viewModel: FavouriteRecipesScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = BottomBarModel.Recipes.title ?: "Нет названия"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onToBack) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        ) {
            items(state.recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClickSave = {
                        viewModel.delete(recipe)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteRecipesScreenPreview() {
    FavouriteRecipesScreen({})
}