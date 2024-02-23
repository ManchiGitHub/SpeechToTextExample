package com.example.myapplication.components

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.MyApplicationTheme
import java.util.Locale

@Preview
@Composable
fun PreviewSpeechToTextScreen() {
    MyApplicationTheme {
        SpeechToTextSection(modifier = Modifier.background(MaterialTheme.colorScheme.background))
    }
}

@Composable
fun SpeechToTextSection(
    modifier: Modifier = Modifier,
) {
    var speechResult by remember { mutableStateOf("") }

    val speechToTextIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something..")
        }
    }

    val speechIntentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                val textArray = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                speechResult = textArray?.get(0) ?: ""
            }
        }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = speechResult.ifEmpty { "Tap the button below to activate the speech recognizer" },
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(25.dp).alpha(0.6f)
        )
        ElevatedButton(
            contentPadding = PaddingValues(25.dp),
            colors = ButtonDefaults.elevatedButtonColors(),
            modifier = Modifier.fillMaxWidth(),
            onClick = { speechIntentLauncher.launch(speechToTextIntent) }
        ) {
            Text(
                text = "Speech to text",
                fontSize = 16.sp
            )
        }
    }
}