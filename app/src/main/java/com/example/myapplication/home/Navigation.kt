package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.navigation.AppNavigation
import theme.MyApplicationTheme

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        startDestination = AppNavigation.SpeechToTextRoute,
        route = AppNavigation.HomeRoute
    ) {
        composable(
            route = AppNavigation.SpeechToTextRoute,
            content = {
                HomeScreen()
            }
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    MyApplicationTheme {
        HomeScreen(Modifier.background(MaterialTheme.colorScheme.background))
    }
}