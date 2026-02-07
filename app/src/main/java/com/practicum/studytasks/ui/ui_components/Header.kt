package com.practicum.studytasks.ui.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Header(
    title: String,
    subtitle: String
) {
    Column {
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