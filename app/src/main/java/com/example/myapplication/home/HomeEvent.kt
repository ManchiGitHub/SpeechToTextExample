package com.example.myapplication.home

import java.util.ArrayList

sealed class HomeEvent {
    data class OnAudioRecorderChecked(val isChecked: Boolean) : HomeEvent()
    data class OnSpeechText(val speechText: ArrayList<String>?) : HomeEvent()
    data class OnAudioPermission(val isGranted: Boolean) : HomeEvent()
}

fun HomeEvent.OnSpeechText.reduceSpeechTextEvent(currentState: HomeUiState) = speechText?.let {
    currentState.copy(speechTextResult = it[0])
} ?: currentState.copy(speechTextResult = "")


fun HomeEvent.OnAudioPermission.reduceAudioPermission(currentState: HomeUiState) =
    currentState.copy(
        isRecordCheck = isGranted,
        isPermissionsError = !isGranted
    )


fun HomeEvent.OnAudioRecorderChecked.reduceRecorderChecked(currentState: HomeUiState) =
    currentState.copy(
        isRecordCheck = isChecked
    )

