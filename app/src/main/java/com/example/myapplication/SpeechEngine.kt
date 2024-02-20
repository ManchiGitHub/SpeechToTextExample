package com.example.myapplication

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SpeechEngine(private val resultRegistry: ActivityResultRegistry) {

    /**
     * A simple method that converts speech to text using Google's built-in speech recognition service.
     * Wrapped inside a suspendCoroutine for easy use.
     */
    suspend fun getTextFromSpeech(): String = suspendCoroutine { cont ->

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        val launcher = resultRegistry.register(
            SPEECH_REQUEST,
            ActivityResultContracts.StartActivityForResult()
        ) {

            if (it.resultCode == RESULT_OK && it.data != null) {
                val textArray = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val text = textArray?.get(0)
                cont.resume(text ?: "")
            } else {
                cont.resume("")
            }
        }

        launcher.launch(intent)
    }

    companion object {
        private const val SPEECH_REQUEST = "SpeechEngine.SPEECH_REQUEST"
    }
}