package com.example.myapplication.record

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R

@Composable
fun RecordLayout(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {

    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    val animatedVisibility = remember { Animatable(0f) }

    LaunchedEffect(lifecycleOwner) {
        animatedVisibility.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200)
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        LottieAnimation(
            composition = lottieComposition,
            progress = progress,
            modifier = Modifier
                .size(100.dp)
                .alpha(animatedVisibility.value)
                .align(Alignment.Center)
                .padding(bottom = 16.dp)
        )
    }
}