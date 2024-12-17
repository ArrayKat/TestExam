package com.example.testexam.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testexam.view.screens.auth.Auth
import com.example.testexam.view.screens.card.Card
import com.example.testexam.view.screens.home.Home
import com.example.testexam.view.screens.splash.Splash

@Composable
fun Navigation (controller: NavHostController, modifier: Modifier) {
    NavHost(
        navController = controller,
        startDestination = NavRotes.SPLASH
    ){
        composable(NavRotes.SPLASH){
            Splash(controller)
        }
        composable(NavRotes.AUTH){
            Auth(controller)
        }
        composable(NavRotes.CARD + "/{id}"){
            x->
            val str = x.arguments?.getString("id") ?: ""
            val id = str.toInt()
            Card(controller,id)
        }
        composable(NavRotes.HOME){
            Home(controller)
        }
    }
}