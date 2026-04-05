package com.practicum.studytasks.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.presentation.home.ProgressBar
import com.practicum.studytasks.presentation.home.StatsGrid
import com.practicum.studytasks.presentation.home.UpcomingTasks
import com.practicum.studytasks.presentation.theme.StudyTasksTheme
import com.practicum.studytasks.presentation.ui_components.Header

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.screen_inner_padding))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_blocks_padding))
    ) {
        item { Header(stringResource(R.string.home), "2 курс, 2 семестр") }
        item { StatsGrid(17, 5, 12, 8) }
        item { ProgressBar(29) }
        item { UpcomingTasks() }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun HomeScreenLightPreview() {
    StudyTasksTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkPreview() {
    StudyTasksTheme {
        HomeScreen()
    }
}
