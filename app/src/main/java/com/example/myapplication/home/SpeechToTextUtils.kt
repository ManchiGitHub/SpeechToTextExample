package com.example.myapplication.home

import android.content.Intent
import android.speech.RecognizerIntent
import java.util.Locale

val SpeechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...")
}