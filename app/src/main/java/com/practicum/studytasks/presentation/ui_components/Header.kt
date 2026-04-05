package com.practicum.studytasks.presentation.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
fun Header(
    title: String,
    subtitle: String
) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle, // добавить отслеживание семестра
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun CustomTextfieldLightPreview() {
    StudyTasksTheme {
        Header(
            title = "Экранчик",
            subtitle = "3 курс, 2 семестр"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CustomTextfieldDarkPreview() {
    StudyTasksTheme {
        Header(
            title = "Экранчик",
            subtitle = "3 курс, 2 семестр"
        )
    }
}