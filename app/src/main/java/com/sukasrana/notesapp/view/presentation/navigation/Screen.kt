package com.sukasrana.notesapp.view.presentation.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Login: Screen("login")
    data object SignUp: Screen("signup")
    data object Notes: Screen("notes/{id}"){
        fun createRoute(id: Int?) = "notes/$id"
    }
    data object User: Screen("user")
    data object Maps: Screen("maps")

    data object Splash : Screen("splash")
}