package com.practicum.studytasks.presentation.navigation

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
import com.practicum.studytasks.presentation.allsubjects.screen.AllSubjectsScreen
import com.practicum.studytasks.presentation.home.screen.HomeScreen
import com.practicum.studytasks.presentation.schedule.screen.ScheduleScreen
import com.practicum.studytasks.presentation.subject.screen.SubjectScreen

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

        composable(Routes.SCHEDULE.name) {
            ScheduleScreen()
        }

        composable(Routes.ALL_SUBJECTS.name) {
            AllSubjectsScreen(onSubjectClick = { navController.navigate(Routes.SUBJECT.name) })
        }

        composable(Routes.SUBJECT.name) {
            SubjectScreen(onBack = { navController.popBackStack() })
        }
    }
}