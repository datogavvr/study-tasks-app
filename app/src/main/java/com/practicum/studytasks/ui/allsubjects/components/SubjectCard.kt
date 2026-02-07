package com.practicum.studytasks.ui.allsubjects.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
fun SubjectCard(
    title: String,
    teacher: String,
    total: Int,
    done: Int,
    percent: Int,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                onClick = {}
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Column(Modifier.padding(dimensionResource(R.dimen.subject_card_inner_padding))) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_icon_text_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.medium_icon_background_size))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)))
                        .background(color.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook, // добавить выбор иконки
                        contentDescription = title,
                        modifier = Modifier.size(dimensionResource(R.dimen.medium_icon_size)),
                        tint = color
                    )
                }

                Column(Modifier.weight(1f)) {
                    Text(title, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(teacher, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = title,
                    modifier = Modifier.size(dimensionResource(R.dimen.medium_icon_size)),
                    tint = Color.Gray
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.medium_between_header_content_padding)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.subject_completed_tasks, done, total),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp
                )
                Text(
                    text = stringResource(R.string.subject_progress_value, percent),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.subject_card_content_padding)))

            LinearProgressIndicator(
                progress = { percent / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.progressbar_line_height))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner))),
                color = color,
                trackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
            )
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun SubjectCardLightPreview() {
    StudyTasksTheme {
        SubjectCard(
            title = "Вайбкодинг",
            teacher = "Месси Л.А.",
            total = 11,
            done = 7,
            percent = 64,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SubjectCardDarkPreview() {
    StudyTasksTheme {
        SubjectCard(
            title = "Вайбкодинг",
            teacher = "Месси Л.А.",
            total = 11,
            done = 7,
            percent = 64,
            color = MaterialTheme.colorScheme.primary
        )
    }
}