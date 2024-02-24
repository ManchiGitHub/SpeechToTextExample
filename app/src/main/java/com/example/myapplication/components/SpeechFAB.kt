package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import theme.MyApplicationTheme

@Composable
@Preview
fun PreviewSpeechFABComponent() {
    MyApplicationTheme {
        SpeechFAB(modifier = Modifier.background(MaterialTheme.colorScheme.background))
    }
}

@Composable
fun SpeechFAB(
    modifier: Modifier,
    launchSpeechRecognizer: () -> Unit = {},
) {
    LargeFloatingActionButton(
        modifier = modifier,
        onClick = launchSpeechRecognizer
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardVoice,
            contentDescription = "Archive action button"
        )
    }
}