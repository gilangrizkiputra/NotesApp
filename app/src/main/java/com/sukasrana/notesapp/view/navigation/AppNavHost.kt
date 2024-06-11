package com.sukasrana.notesapp.view.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.view.auth.LoginScreen
import com.sukasrana.notesapp.view.auth.SignupScreen
import com.sukasrana.notesapp.view.home.HomeScreen
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