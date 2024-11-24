package com.example.povar.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.povar.presentation.ui.theme.Typography

@Composable
fun BottomBar(
    navController: NavHostController,
    viewModel: BottomBarScreenViewModel?
) {
    val items = listOf(
        BottomBarModel.Recipes,
        BottomBarModel.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination
    val query = viewModel?.query?.collectAsState()?.value ?: ""

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (query.isNotEmpty() && screen == BottomBarModel.Recipes) {
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor = Color.Black
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (destination?.hierarchy?.any { it.route == screen.route } == true) {
                                    screen.setIcon
                                } else {
                                    screen.unsetIcon
                                } ?: Icons.TwoTone.Info,
                                contentDescription = screen.title,
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = screen.title ?: "Неизвестно",
                        style = Typography.bodySmall,
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        navController = rememberNavController(),
        null
    )
}