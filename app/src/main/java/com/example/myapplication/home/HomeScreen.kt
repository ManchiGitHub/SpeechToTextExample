package com.example.myapplication.home

import android.app.Activity
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var speechTextResult by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val textArray =
                    result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val speechResult = textArray?.get(0) ?: ""
                speechTextResult = speechResult
            }
        }
    )

    HomeLayout(
        modifier = modifier.padding(16.dp),
        snackbarHostState = snackbarHostState,
        onLaunchSpeechToText = {
            launcher.launch(SpeechIntent)
        }
    )
}



