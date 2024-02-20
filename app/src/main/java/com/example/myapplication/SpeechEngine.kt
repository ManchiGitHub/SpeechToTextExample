package com.example.myapplication

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.CancellationException
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SpeechEngine(private val resultRegistry: ActivityResultRegistry) {

    /**
     * A simple method that converts speech to text using Google's built-in speech recognition service.
     * Wrapped inside a suspendCoroutine for easy use.
     */
    suspend fun getTextFromSpeech(): String = suspendCoroutine { cont ->

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {

            // Handle natural, unstructured speech
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // Set recognition language to device's default
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }

        val launcher = resultRegistry.register(
            SPEECH_REQUEST,
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            // Process the speech recognition result
            if (activityResult.resultCode == RESULT_OK && activityResult.data != null) {
                val text = extractText(activityResult)
                cont.resume(text)
            } else {
                cont.resume("")
            }
        }

        try {
            // Start the speech recognition intent
            launcher.launch(intent)
        } catch (ex: CancellationException){
            throw ex
        } catch (ex: Exception){
            cont.resumeWithException(ex)
        }
    }

    private fun extractText(activityResult: ActivityResult): String {
        val textArray = activityResult.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

        // First result is typically the most accurate
        return textArray?.get(0) ?: ""
    }

    companion object {
        private const val SPEECH_REQUEST = "SpeechEngine.SPEECH_REQUEST"
    }
}