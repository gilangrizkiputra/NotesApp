package com.sukasrana.notesapp.view.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Splash : Screen("splash")
}