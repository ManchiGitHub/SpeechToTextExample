package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.common.ScaffoldWithSnackbar
import com.example.myapplication.components.InstructionsText
import kotlinx.coroutines.flow.filter
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeLayout() {
    val navController = rememberNavController()
    MyApplicationTheme {
        HomeScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            navController
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

    LaunchedEffect(isRecordCheck){

    }

    ScaffoldWithSnackbar(
        snackbarHostState = snackbarHostState,
        floatingActionButton = {
            LargeFloatingActionButton(
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                InstructionsText(
                    text = stringResource(R.string.switch_to_use_the_microphone_directly)
                )
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