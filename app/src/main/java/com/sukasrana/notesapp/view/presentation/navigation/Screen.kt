package com.sukasrana.notesapp.view.presentation.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Notes: Screen("notes/{id}"){
        fun createRoute(id: Int?) = "notes/$id"
    }
}