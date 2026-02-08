package com.practicum.studytasks.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.Green
import com.practicum.studytasks.ui.theme.Peach
import com.practicum.studytasks.ui.theme.Red
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
fun UpcomingTasks() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_between_cards_padding))
    ) {
        Text(
            stringResource(R.string.upcoming_tasks),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )


        TaskItem("Домашнее задание по английскому", "Английский язык", "Завтра", Peach)
        TaskItem("Домашнее задание по истории", "История", "Через 3 дн.", Red)
        TaskItem("Лабораторная работа №2", "Физика", "10 февр.", Green)
    }
}

@Composable
private fun TaskItem(title: String, subject: String, deadline: String, color: Color) {
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
        Row(
            modifier = Modifier
                .height(dimensionResource(R.dimen.no_description_task_card_height))
                .padding(horizontal = dimensionResource(R.dimen.task_card_inner_padding)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            VerticalDivider(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.card_inner_padding))
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(50)),
                thickness = dimensionResource(R.dimen.task_card_line_width),
                color = color)
            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.task_card_between_content_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.task_card_between_content_padding))
            ) {
                Text(title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                            color = color.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner))
                            )
                    ) {
                        Text(
                            text = subject,
                            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.text_background_padding)),
                            color = color,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(stringResource(R.string.deadline, deadline), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun UpcomingTasksLightPreview() {
    StudyTasksTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            UpcomingTasks()
            Spacer(modifier = Modifier.weight(0.75f))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun UpcomingTasksDarkPreview() {
    StudyTasksTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            UpcomingTasks()
            Spacer(modifier = Modifier.weight(0.75f))
        }
    }
}

