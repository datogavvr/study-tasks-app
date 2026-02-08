package com.practicum.studytasks.ui.allsubjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.allsubjects.components.SubjectCard
import com.practicum.studytasks.ui.theme.Blue
import com.practicum.studytasks.ui.theme.Green
import com.practicum.studytasks.ui.theme.Peach
import com.practicum.studytasks.ui.theme.Purple
import com.practicum.studytasks.ui.theme.Red
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import com.practicum.studytasks.ui.ui_components.Header

@Composable
fun AllSubjectsScreen(
) {
    val subjects = listOf(
        listOf("Математический анализ", "Харламов Г.М.", 5, 1, Blue),
        listOf("Программирование", "Благовещенский И.Г.", 5, 2, Purple),
        listOf("Физика", "Виленкин Х.З.", 3, 1, Green),
        listOf("Английский язык", "Ким Ю.Х.", 2, 1, Peach),
        listOf("История", "Неважный Ч.Т.", 2, 0, Red),
    ) // добавить возможность менять предметы
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.home_screen_inner_padding))
            .padding(horizontal = dimensionResource(R.dimen.home_screen_inner_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.home_screen_blocks_padding)),
        horizontalAlignment = Alignment.Start
    ) {
        Header(stringResource(R.string.subjects), "2 курс, 2 семестр") // добавить отслеживание семестра

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_between_cards_padding))
        ) {
            items(subjects) { item ->
                val total = item[2] as Int
                val done = item[3] as Int

                val percent = (done.toFloat() / total.toFloat() * 100).toInt()

                SubjectCard(
                    title = item[0] as String,
                    teacher = item[1] as String,
                    total = total,
                    done = done,
                    percent = percent,
                    color = item[4] as Color
                )
            }
            item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_between_cards_padding))) }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun AllSubjectsScreenLightPreview() {
    StudyTasksTheme {
        AllSubjectsScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AllSubjectsScreenDarkPreview() {
    StudyTasksTheme {
        AllSubjectsScreen()
    }
}