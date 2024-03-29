package com.example.myapplication.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun ScaffoldWithSnackbar(
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState,
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        floatingActionButton = floatingActionButton,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = content
    )
}