package com.sukasrana.notesapp.view.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.view.presentation.navigation.NotesNavGraph
import com.sukasrana.notesapp.viewModel.AuthViewModel


@Composable
fun NotesApp(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NotesNavGraph( viewModel = viewModel,navController = navController, modifier = modifier)
}