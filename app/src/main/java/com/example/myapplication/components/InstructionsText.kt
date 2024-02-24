package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewSpeechToTextScreen() {
    MyApplicationTheme {
        InstructionsText(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
fun InstructionsText(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize : TextUnit = 18.sp
) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        fontSize = fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.alpha(0.6f)
    )
}