package com.practicum.studytasks.presentation.subject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.presentation.subject.SubjectHeader
import com.practicum.studytasks.presentation.subject.SubjectProgressBar
import com.practicum.studytasks.presentation.subject.TaskBox
import com.practicum.studytasks.presentation.subject.TopBar
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
fun SubjectScreen(
    color: Color = MaterialTheme.colorScheme.primary,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color.copy(alpha = 0.2f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TopBar(onBack = onBack)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_blocks_padding))
        ) {
            item { SubjectHeader("Вайбкодинг", "Месси Л.А.", "Г-307-1", Icons.Filled.Code) }
            item { SubjectProgressBar(3, 1, 33) }
            item { TaskBox() }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun AllSubjectsScreenLightPreview() {
    StudyTasksTheme {
        SubjectScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AllSubjectsScreenDarkPreview() {
    StudyTasksTheme {
        SubjectScreen()
    }
}