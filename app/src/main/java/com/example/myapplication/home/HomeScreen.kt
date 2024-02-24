package com.example.myapplication.home

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
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
                .background(MaterialTheme.colorScheme.background),
            navController = navController
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    speechToText: SpeechToText = SpeechToText(),
    navController: NavController,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var speechTextResult by remember { mutableStateOf("") }
    val speechLauncher = speechToText.rememberSpeechIntentLauncher {
        speechTextResult = it
    }

    HomeLayout(
        modifier = modifier.padding(16.dp),
        snackbarHostState = snackbarHostState,
        onLaunchSpeechToText = { speechLauncher.launch(speechToText.intent) }
    )
}

