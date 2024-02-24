package com.example.myapplication

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.record.recordNavGraph
import com.example.myapplication.home.homeNavGraph
import com.example.myapplication.navigation.NavGraphRoutes
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewSpeechToTextScreen() {
    val navController = rememberNavController()
    MyApplicationTheme {
        MainComposable(navController = navController)
    }
}

@Composable
fun MainComposable(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
        NavHost(
            navController = navController,
            startDestination = NavGraphRoutes.Home.name
        ) {
            homeNavGraph(navController, snackbarHostState)
            recordNavGraph(navController)
        }
}