package com.example.myapplication.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object NavigationTransitions {

    fun defaultEnterTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween()
        ) + fadeIn(animationSpec = tween(easing = FastOutSlowInEasing))
    }

    fun defaultExitTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.End,
            tween()
        ) + fadeOut(animationSpec = tween(easing = FastOutSlowInEasing, durationMillis = 100))
    }
}
