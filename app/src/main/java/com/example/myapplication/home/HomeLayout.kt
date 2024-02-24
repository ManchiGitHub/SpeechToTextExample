package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.common.ScaffoldWithSnackbar
import com.example.myapplication.components.InstructionsText
import kotlinx.coroutines.flow.filter
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeLayout() {
    MyApplicationTheme {
        HomeLayout(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    speechToText: SpeechToText = SpeechToText()
) {
    val speechTextResult = remember { mutableStateOf("") }
    val speechSnapshot = snapshotFlow { speechTextResult.value }
    val speechLauncher = speechToText.rememberSpeechIntentLauncher {
        speechTextResult.value = it
    }

    LaunchedEffect(Unit) {
        speechSnapshot
            .filter { it.isNotEmpty() }
            .collect { snackbarHostState.showSnackbar(speechTextResult.value) }
    }

    ScaffoldWithSnackbar(
        snackbarHostState = snackbarHostState,
        floatingActionButton = {
            LargeFloatingActionButton(
                modifier = modifier,
                onClick = {
                    speechLauncher.launch(speechToText.intent)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardVoice,
                    contentDescription = "Speech to Text action button"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            InstructionsText(
                modifier = modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                fallbackText = "Tap the button below to activate the speech recognizer"
            )
        }
    }
}