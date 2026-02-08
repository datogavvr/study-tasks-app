package com.practicum.studytasks.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import com.practicum.studytasks.ui.addtask.screen.AddTaskDialog
import com.practicum.studytasks.ui.ui_components.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    StudyTasksTheme {
        val navController = rememberNavController()
        var showAddTaskDialog by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = { BottomBar(
                onHomeClick = { navController.navigate(Routes.HOME.name) },
                onAddTaskClick = { showAddTaskDialog = true },
                onSubjectsClick = { navController.navigate(Routes.ALL_SUBJECTS.name) }
            ) },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            AppNavGraph(
                navController = navController,
                padding = padding
            )

            if (showAddTaskDialog) {
                AddTaskDialog(
                    onDismiss = { showAddTaskDialog = false }
                )
            }
        }
    }
}