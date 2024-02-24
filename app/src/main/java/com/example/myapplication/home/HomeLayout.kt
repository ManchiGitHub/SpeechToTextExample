package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.common.ScaffoldWithSnackbar
import com.example.myapplication.components.InstructionsText
import kotlinx.coroutines.flow.filter
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeLayout() {
    MyApplicationTheme {
        HomeLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        )
    }
}

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    speechToText: SpeechToText = SpeechToText()
) {
    var speechTextResult by remember { mutableStateOf("") }
    val speechSnapshot = snapshotFlow { speechTextResult }
    val speechLauncher = speechToText.rememberSpeechIntentLauncher {
        speechTextResult = it
    }
    var isRecordCheck by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        speechSnapshot
            .filter { it.isNotEmpty() }
            .collect { snackbarHostState.showSnackbar(speechTextResult) }
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
                    contentDescription = stringResource(R.string.speech_to_text_action_button)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .align(Alignment.TopCenter)
            ) {
                InstructionsText(
                    text = stringResource(R.string.switch_to_use_the_microphone_directly)
                )
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Switch(
                    checked = isRecordCheck,
                    onCheckedChange = {
                        isRecordCheck = it
                    }
                )
            }

        }
    }
}