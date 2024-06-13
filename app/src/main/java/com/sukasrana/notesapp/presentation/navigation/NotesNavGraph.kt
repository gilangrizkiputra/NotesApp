package com.sukasrana.notesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sukasrana.notesapp.presentation.home.homeScreenRoute
import com.sukasrana.notesapp.presentation.notes.notesScreenRoute
import com.sukasrana.notesapp.presentation.splash.splashScreenRoute

@Composable
fun NotesNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        splashScreenRoute(navController)
        homeScreenRoute(navController)
        notesScreenRoute(navController)
    }
}