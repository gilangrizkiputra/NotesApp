package com.sukasrana.notesapp.view.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.navigation.Screen

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(route = Screen.Home.route) {
        HomeScreen(navController)
    }
}