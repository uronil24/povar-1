package com.example.povar.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.povar.presentation.navigation.BottomBarScreen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PovarApplication : Application(){
    @Composable
    fun PovarNavHost(){
        val navController = rememberNavController()
        BottomBarScreen(
            navController = navController
        )
    }
}