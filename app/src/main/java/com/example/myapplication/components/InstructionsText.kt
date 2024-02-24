package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewSpeechToTextScreen() {
    MyApplicationTheme {
        InstructionsText(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            text = stringResource(R.string.enable_recording)
        )
    }
}

@Composable
fun InstructionsText(
    modifier: Modifier = Modifier,
    text: String = "",
    subtext: String = "",
    asteriskText: String = "",
    textSize: TextUnit = 18.sp
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.alpha(0.8f),
            text = text,
            textAlign = TextAlign.Start,
            fontSize = textSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .alpha(0.6f)
                .padding(top = 8.dp),
            lineHeight = textSize,
            text = subtext,
            fontSize = textSize.div(1.5)
        )
        Text(
            modifier = Modifier
                .alpha(0.6f).padding(3.dp),
            text = asteriskText,
            fontSize = textSize.div(1.7)
        )
    }

}