package com.sukasrana.notesapp.view.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sukasrana.notesapp.view.presentation.ListUser.listUserScreenRoute
import com.sukasrana.notesapp.view.presentation.auth.loginRoute
import com.sukasrana.notesapp.view.presentation.auth.signUpRoute
import com.sukasrana.notesapp.view.presentation.home.homeScreenRoute
import com.sukasrana.notesapp.view.presentation.maps.mapsScreenRoute
import com.sukasrana.notesapp.view.presentation.notes.notesScreenRoute
import com.sukasrana.notesapp.view.presentation.splash.splashScreenRoute
import com.sukasrana.notesapp.viewModel.AuthViewModel

@Composable
fun NotesNavGraph(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        loginRoute(viewModel,navController)
        signUpRoute(viewModel, navController)
        splashScreenRoute(navController)
        homeScreenRoute(viewModel,navController)
        notesScreenRoute(navController)
        listUserScreenRoute(navController)
        mapsScreenRoute(navController)
    }
}