package com.example.testexam.view.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.testexam.view.navigation.NavRotes
import kotlinx.coroutines.delay

@Composable
fun Splash (navHostController: NavHostController) {
    LaunchedEffect(Unit)
    {
        delay(2000L)
        navHostController.navigate(NavRotes.AUTH){
            popUpTo(NavRotes.SPLASH){
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = "СПЛЭШ ЭКРАН")
    }
}