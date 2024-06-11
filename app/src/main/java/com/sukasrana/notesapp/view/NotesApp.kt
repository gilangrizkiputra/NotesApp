package com.sukasrana.notesapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.view.navigation.Screen
import com.sukasrana.notesapp.view.screen.home.Home
import com.sukasrana.notesapp.view.screen.splash.SplashScreen

@Composable
fun NotesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = modifier.padding(it)
        ) {
            composable(Screen.Splash.route){
                SplashScreen(navController)
            }
            composable(Screen.Home.route){
                Home()
            }
        }
    }
}