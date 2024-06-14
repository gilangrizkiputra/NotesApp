package com.sukasrana.notesapp.view.presentation.maps

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.ListUser.ListUserScreen
import com.sukasrana.notesapp.view.presentation.navigation.Screen

fun NavGraphBuilder.mapsScreenRoute(navController: NavController) {
    composable(route = Screen.Maps.route) {
        LocationScreen(navController)
    }
}