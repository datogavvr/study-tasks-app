package com.practicum.studytasks.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practicum.studytasks.ui.allsubjects.AllSubjectsScreen
import com.practicum.studytasks.ui.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME.name,
        modifier = Modifier
            .padding(padding),
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(Routes.HOME.name) {
            HomeScreen()
        }

        composable(Routes.ALL_SUBJECTS.name) {
            AllSubjectsScreen()
        }
    }
}