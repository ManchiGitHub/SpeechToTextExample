package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        SettingSwitch(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp),
            isRecordCheck = true,
            text = stringResource(R.string.enable_recording),
            subtext = stringResource(R.string.flip_the_switch_to_use_the_mediarecorder),
            asteriskText = stringResource(R.string.requires_audio_recording_permissions),
        )
    }
}

@Composable
fun SettingSwitch(
    modifier: Modifier = Modifier,
    text: String = "",
    subtext: String = "",
    asteriskText: String = "",
    textSize: TextUnit = 18.sp,
    isRecordCheck: Boolean,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.alpha(0.8f),
                text = text,
                textAlign = TextAlign.Start,
                fontSize = textSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Switch(
                modifier = Modifier,
                checked = isRecordCheck,
                onCheckedChange = { onCheckedChange(it) }
            )
        }
        Text(
            modifier = Modifier
                .alpha(0.6f).fillMaxWidth(0.9f),
            lineHeight = textSize,
            text = subtext,
            fontSize = textSize.div(1.5)
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.9f)
                .alpha(0.6f),
            lineHeight = textSize,
            text = asteriskText,
            fontSize = textSize.div(1.7)
        )
    }
}