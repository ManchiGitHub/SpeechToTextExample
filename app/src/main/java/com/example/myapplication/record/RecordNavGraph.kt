package com.example.myapplication.record

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.navigation.ArchiveNavOptions
import com.example.myapplication.navigation.NavGraphRoutes
import com.example.myapplication.navigation.NavigationTransitions

typealias ScreenEnterTransition = AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
typealias ScreenExitTransition = AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?

fun NavGraphBuilder.recordNavGraph(
    navController: NavHostController,
    enterTransition: ScreenEnterTransition = NavigationTransitions.defaultEnterTransition(),
    exitTransition: ScreenExitTransition = NavigationTransitions.defaultExitTransition()
) {
    navigation(
        startDestination = ArchiveNavOptions.RecentEntries.name,
        route = NavGraphRoutes.Archive.name
    ) {
        composable(
            route = ArchiveNavOptions.RecentEntries.name,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            content = { RecordScreen(navController = navController) }
        )
    }
}