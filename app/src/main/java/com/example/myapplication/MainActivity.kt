package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val speechEngine by lazy {
        SpeechEngine(activityResultRegistry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* --- THE IMPORTANT PART --- */
        binding.voiceButton.setOnClickListener {
            lifecycleScope.launch {
                val text = speechEngine.getTextFromSpeech()
                binding.inputTarget.text = text
            }
        }

        /* Not Important */
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bottomInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, bottomInsets)
            insets
        }
    }
}