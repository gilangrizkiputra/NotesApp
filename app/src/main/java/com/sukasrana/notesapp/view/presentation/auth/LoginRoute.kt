package com.sukasrana.notesapp.view.presentation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.navigation.Screen
import com.sukasrana.notesapp.viewModel.AuthViewModel

fun NavGraphBuilder.loginRoute(viewModel: AuthViewModel, navController: NavController) {
    composable(route = Screen.Login.route) {
        LoginScreen(viewModel, navController)
    }
}