package com.sukasrana.notesapp.view.presentation.notes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukasrana.notesapp.view.presentation.navigation.Screen

fun NavGraphBuilder.notesScreenRoute(navController: NavController) {
    composable(
        route = Screen.Notes.route,
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    ) {
        val notesId = it.arguments?.getInt("id") ?: 0
        NotesScreen(id = notesId ?: 0, navController)
    }
}