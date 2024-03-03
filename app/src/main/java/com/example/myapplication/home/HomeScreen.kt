package com.example.myapplication.home

import android.Manifest
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    viewModel: HomeViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val homeViewModel: HomeViewModel = hiltViewModel()
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val speechLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val textArray = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                homeViewModel.onEvent(HomeEvent.OnSpeechText(textArray))
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            homeViewModel.onEvent(HomeEvent.OnAudioPermission(isGranted))
        }
    )

    val launchRecordAudioPermission = {
        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    HomeLayout(
        modifier = modifier.padding(16.dp),
        snackbarHostState = snackbarHostState,
        isRecordCheck = uiState.isRecordCheck,
        onLaunchSpeechToText = { speechLauncher.launch(SpeechIntent) },
        onLaunchPermission = { launchRecordAudioPermission() },
        onCheckedChange = { viewModel.onEvent(HomeEvent.OnAudioRecorderChecked(it)) }
    )
}



