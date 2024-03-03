package com.example.myapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = Channel<HomeEvent>()
    private val eventFlow = _eventFlow.receiveAsFlow()

    init {
        viewModelScope.launch {
            eventFlow.collect(::reduce)
        }
    }

    private fun reduce(homeEvent: HomeEvent) {
        _uiState.update {
            with(homeEvent) {
                when (this) {
                    is HomeEvent.OnSpeechText -> reduceSpeechTextEvent(it)
                    is HomeEvent.OnAudioPermission -> reduceAudioPermission(it)
                    is HomeEvent.OnAudioRecorderChecked -> reduceRecorderChecked(it)
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }
}