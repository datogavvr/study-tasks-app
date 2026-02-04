package com.practicum.studytasks.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.practicum.studytasks.R

@Composable
fun Header() {
    Column {
        Text(
            text = stringResource(R.string.home),
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            "2 курс, 2 семестр", // добавить отслеживание семестра
            color = Color.Gray
        )
    }
}