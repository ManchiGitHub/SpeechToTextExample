package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    MyApplicationTheme {
        HomeScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
            ,
            navController = navController
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    HomeLayout(
        modifier = modifier.padding(16.dp),
    )
}

