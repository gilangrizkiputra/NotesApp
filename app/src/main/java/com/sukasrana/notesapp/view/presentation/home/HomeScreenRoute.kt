package com.sukasrana.notesapp.view.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.navigation.Screen
import com.sukasrana.notesapp.viewModel.AuthViewModel

fun NavGraphBuilder.homeScreenRoute(viewModel: AuthViewModel,navController: NavController) {
    composable(route = Screen.Home.route) {
        HomeScreen(viewModel, navController)
    }
}