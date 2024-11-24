@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.povar.presentation.listRecipe.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.povar.presentation.listRecipe.viewModel.RecipesScreenViewModel
import com.example.povar.presentation.model.RecipeItem
import com.example.povar.presentation.navigation.BottomBarModel

@Composable
fun RecipesScreen(
    onToFavourite: () -> Unit,
    viewModel: RecipesScreenViewModel = hiltViewModel()
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
                actions = {
                    IconButton(onClick = {
                        onToFavourite()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.StarOutline, contentDescription = null
                        )
                    }
                })
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.padding(end = 16.dp)) {
                    Text(text = "Салат", style = MaterialTheme.typography.bodyMedium)
                    Switch(
                        checked = state.filter == "Салат",
                        onCheckedChange = {
                            viewModel.toggleFilter("Салат", it)
//                            if (!it && state.filter == "Салат") {
//                                viewModel.sort("", it)
//                            } else {
//                                viewModel.sort("Салат", it)
//                            }
                        }
                    )
                }
                Column(modifier = Modifier.padding(end = 16.dp)) {
                    Text(text = "Завтрак", style = MaterialTheme.typography.bodyMedium)
                    Switch(
                        checked = state.filter == "Завтрак",
                        onCheckedChange = {
                            viewModel.toggleFilter("Завтрак", it)
                        }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.recipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClickSave = {
                            viewModel.save(recipe)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeCard(
    recipe: RecipeItem,
    onClickSave: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = recipe.desc,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = recipe.category,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = recipe.ingredients,
                style = MaterialTheme.typography.bodyMedium
            )
            IconButton(
                onClick = onClickSave
            ) {
                Icon(
                    imageVector = Icons.Default.Star, contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesScreenPreview() {
    RecipesScreen({})
}