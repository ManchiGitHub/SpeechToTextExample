package com.example.myapplication.archive

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.navigation.ArchiveNavOptions
import com.example.myapplication.navigation.NavGraphRoutes

fun NavGraphBuilder.archiveNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ArchiveNavOptions.RecentEntries.name,
        route = NavGraphRoutes.Archive.name
    ) {
        composable(
            route = ArchiveNavOptions.RecentEntries.name,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween()
                )  + fadeOut(tween(easing = FastOutSlowInEasing, durationMillis = 100))
            },
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween()
                ) + fadeIn(tween(easing = FastOutSlowInEasing))
            },
            content = {
                ArchiveScreen(navController = navController)
            }
        )
    }
}