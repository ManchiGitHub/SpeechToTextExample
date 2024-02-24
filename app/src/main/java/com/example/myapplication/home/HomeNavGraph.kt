package com.example.myapplication.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.navigation.HomeNavOptions
import com.example.myapplication.navigation.NavGraphRoutes

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        startDestination = HomeNavOptions.Main.name,
        route = NavGraphRoutes.Home.name
    ) {
        composable(
            route = HomeNavOptions.Main.name,
            content = {
                HomeScreen(navController = navController)
            }
        )
    }
}