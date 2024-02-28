package com.example.myapplication.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.components.SettingSwitch
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeLayout() {
    val navController = rememberNavController()
    MyApplicationTheme {
        HomeLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        )
    }
}

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isRecordCheck: Boolean = false,
    onLaunchSpeechToText: () -> Unit = {},
    onLaunchPermission: () -> Unit = {},
    onCheckedChange: (isChecked: Boolean) -> Unit = {},
    onRecordPermissionGranted: ()->Unit = {}
) {
    val context = LocalContext.current
    LaunchedEffect(isRecordCheck) {
        if (isRecordCheck) {
            val isRecordAudioPermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED

            if (isRecordAudioPermissionGranted) {
                // recording permissions is granted
                onRecordPermissionGranted()
            } else {
                // Request permission
                onLaunchPermission()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { onLaunchSpeechToText() }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardVoice,
                    contentDescription = stringResource(R.string.speech_to_text_action_button)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SettingSwitch(
                textSize = 20.sp,
                text = stringResource(R.string.enable_recording),
                subtext = stringResource(R.string.flip_the_switch_to_use_the_mediarecorder),
                asteriskText = stringResource(R.string.requires_audio_recording_permissions),
                isRecordCheck = isRecordCheck,
                onCheckedChange = { onCheckedChange(it) }
            )
        }
    }
}