package com.example.myapplication.record

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
@Preview
@Composable
fun PreviewRecordLayout() {
    RecordLayout(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
}

@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    RecordLayout(modifier = modifier)
}

