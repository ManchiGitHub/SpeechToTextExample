package com.example.myapplication

import androidx.compose.runtime.Composable
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
    NavHost(
        navController = navController,
        startDestination = NavGraphRoutes.Home.name
    ) {
        homeNavGraph(navController)
        recordNavGraph(navController)
    }
}