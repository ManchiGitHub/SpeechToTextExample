package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.SpeechToTextSection
import com.example.myapplication.navigation.NavGraphRoutes
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeScreen() {
    MyApplicationTheme {
        val navController = rememberNavController()
        HomeScreen(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            navController = navController
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val navigateToArchive = {
        navController.navigate(NavGraphRoutes.Archive.name) {
            launchSingleTop = true
        }
    }

    HomeLayout(modifier = modifier, navigateToArchive = navigateToArchive)
}

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    navigateToArchive: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        SpeechToTextSection(modifier = Modifier.align(Alignment.Center))
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomStart), // Position the FAB
            onClick = navigateToArchive
        ) {
            Icon(imageVector = Icons.Filled.Archive, contentDescription = "Archive action button")
        }
    }
}