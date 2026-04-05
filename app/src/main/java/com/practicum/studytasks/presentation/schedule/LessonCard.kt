package com.practicum.studytasks.presentation.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.domain.model.LessonType
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
internal fun LessonCard(type: LessonType, title: String, lessonNumber: Int, classroom: String, teacher: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner))),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box( // TODO: точка или полоска
                modifier = Modifier
                    .size(12.dp)
                    .background(type.color, CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            Column { // TODO: внедрить данные с БД
                Text(
                    text = type.label,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "$lessonNumber пара · 9:00 – 10:30",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "$classroom · $teacher",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun LessonCardLightPreview() {
    StudyTasksTheme {
        LessonCard(
            type = LessonType.PRACTICE,
            title = "Вайбкодинг",
            lessonNumber = 2,
            classroom = "Г-307-1",
            teacher = "Неймар Джуниор"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LessonCardDarkPreview() {
    StudyTasksTheme {
        LessonCard(
            type = LessonType.PRACTICE,
            title = "Вайбкодинг",
            lessonNumber = 2,
            classroom = "Г-307-1",
            teacher = "Неймар Джуниор"
        )
    }
}