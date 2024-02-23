package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.archive.archiveNavGraph
import com.example.myapplication.home.homeNavGraph
import com.example.myapplication.navigation.NavGraphRoutes
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewSpeechToTextScreen(){
    val navController = rememberNavController()
    MyApplicationTheme {
        MainComposable(navController = navController)
    }
}

@Composable
fun MainComposable(navController: NavHostController) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = NavGraphRoutes.Home.name
        ) {
            homeNavGraph(navController)
            archiveNavGraph(navController)
        }
    }
}