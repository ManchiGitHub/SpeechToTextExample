package com.example.myapplication.home

data class HomeUiState(
    val isPermissionsError: Boolean = false,
    val isRecordCheck: Boolean = false,
    val speechTextResult: String = ""
)