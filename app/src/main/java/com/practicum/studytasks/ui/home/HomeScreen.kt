package com.practicum.studytasks.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.ui_components.BottomBar
import com.practicum.studytasks.ui.home.components.Header
import com.practicum.studytasks.ui.home.components.ProgressBar
import com.practicum.studytasks.ui.home.components.StatsGrid
import com.practicum.studytasks.ui.home.components.UpcomingTasks
import com.practicum.studytasks.ui.theme.LightGray

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { BottomBar() },
        containerColor = LightGray
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = dimensionResource(R.dimen.home_screen_inner_padding))
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.home_screen_blocks_padding))
        ) {
            item { Header() }
            item { StatsGrid(17, 5, 12, 8) }
            item { ProgressBar(29) }
            item { UpcomingTasks() }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, locale = "ru")
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}
