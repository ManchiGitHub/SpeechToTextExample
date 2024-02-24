package com.example.myapplication.home

import android.Manifest
import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.components.InstructionsText
import theme.MyApplicationTheme

@Preview
@Composable
fun PreviewHomeLayout() {
    val navController = rememberNavController()
    MyApplicationTheme {
        HomeLayout(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        )
    }
}

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onLaunchSpeechToText: () -> Unit = {},
) {
    val context = LocalContext.current

    var isRecordCheck by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            isRecordCheck = isGranted
            if (isGranted) {
                // Permission granted, proceed with recording
            } else {
                // Permission denied, explain the necessity of the permission
                errorMessage =
                    "Permission needed for recording. Please enable it in settings."
            }
        }
    )

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            val result = snackbarHostState.showSnackbar(
                message = errorMessage,
                actionLabel = "Settings",
                duration = SnackbarDuration.Long
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
                    with(intent) {
                        data = Uri.fromParts("package", context.packageName, null)
                        addCategory(CATEGORY_DEFAULT)
                        addFlags(FLAG_ACTIVITY_NEW_TASK)
                        addFlags(FLAG_ACTIVITY_NO_HISTORY)
                        addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                    }
                    context.startActivity(intent)
                }

                SnackbarResult.Dismissed -> {}
            }

            errorMessage = ""
        }
    }

    LaunchedEffect(isRecordCheck) {
        if (isRecordCheck) {
            val isRecordAudioPermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED

            if (isRecordAudioPermissionGranted) {
                Toast.makeText(context, "Permission already granted", Toast.LENGTH_SHORT).show()
                // Permission already granted, proceed with functionality

            } else {
                // Request permission
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = onLaunchSpeechToText
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                InstructionsText(
                    text = stringResource(R.string.switch_to_use_the_microphone_directly)
                )
                Switch(
                    checked = isRecordCheck,
                    onCheckedChange = { isRecordCheck = it }
                )
            }

        }
    }
}