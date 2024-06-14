package com.sukasrana.notesapp.view.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.view.presentation.auth.LoginScreen
import com.sukasrana.notesapp.view.presentation.auth.SignupScreen
import com.sukasrana.notesapp.view.presentation.home.HomeScreen
import com.sukasrana.notesapp.viewModel.AuthViewModel

@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(viewModel,navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(viewModel,navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(viewModel, navController)
        }
    }
}