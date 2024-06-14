package com.sukasrana.notesapp.view.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.R
import com.sukasrana.notesapp.ui.theme.NotesAppTheme
import com.sukasrana.notesapp.view.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 800
                )
            )
            delay(1500L)
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    )

    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = isVisible) {
        delay(250L)
        isVisible = true

        delay(1400L)
        isVisible = false
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = scaleIn(),
                exit = fadeOut()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.notesapp_logo),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    NotesAppTheme {
        SplashScreen(navController = rememberNavController())
    }
}