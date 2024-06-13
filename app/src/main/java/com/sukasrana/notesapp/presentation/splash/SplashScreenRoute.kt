package com.sukasrana.notesapp.presentation.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sukasrana.notesapp.presentation.navigation.Screen

fun NavGraphBuilder.splashScreenRoute(navController: NavController){
    composable(route = Screen.Splash.route) {
        SplashScreen(navController)
    }
}