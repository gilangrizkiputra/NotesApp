package com.sukasrana.notesapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Splash: Screen("splash")
    data object Notes: Screen("notes/{id}"){
        fun createRoute(id: Int?) = "notes/$id"
    }
}