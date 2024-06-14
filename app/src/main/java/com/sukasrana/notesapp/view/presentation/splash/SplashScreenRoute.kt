package com.sukasrana.notesapp.view.presentation.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.view.presentation.maps.LocationScreen
import com.sukasrana.notesapp.view.presentation.navigation.Screen

fun NavGraphBuilder.splashScreenRoute(navController: NavController) {
    composable(route = Screen.Splash.route) {
        SplashScreen(navController)
    }
}