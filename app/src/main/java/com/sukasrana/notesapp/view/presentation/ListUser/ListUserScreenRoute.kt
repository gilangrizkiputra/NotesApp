package com.sukasrana.notesapp.view.presentation.ListUser

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.home.HomeScreen
import com.sukasrana.notesapp.view.presentation.navigation.Screen

fun NavGraphBuilder.listUserScreenRoute(navController: NavController) {
    composable(route = Screen.User.route) {
        ListUserScreen(navController)
    }
}