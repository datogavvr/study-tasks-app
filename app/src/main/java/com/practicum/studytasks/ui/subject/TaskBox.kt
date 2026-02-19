package com.practicum.studytasks.ui.subject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.Green
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
internal fun TaskBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                topStart = dimensionResource(R.dimen.large_rounded_corner),
                topEnd = dimensionResource(R.dimen.large_rounded_corner)))
            .background(MaterialTheme.colorScheme.background)
            .padding(top = dimensionResource(R.dimen.content_blocks_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_blocks_padding))
    ) {
        TaskType("Курсовая работа", 1)
        TaskType("Практическая работа", 2)
        TaskType("Домашнее задание", 2)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_between_cards_padding)))
    }
}

@Composable
private fun TaskType(
    taskType: String = "Домашнее задание",
    countUncompleted: Int = 2
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.screen_inner_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_between_cards_padding))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = taskType,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Card(
                shape = RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                border = BorderStroke(
                    width = dimensionResource(R.dimen.card_border_width),
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                Text(
                    text = countUncompleted.toString(),
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.text_background_padding)),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        SubjectTaskItem("Домашнее задание №1", "Вычисление пределов", "Завтра", true)
        SubjectTaskItem("Домашнее задание №2", null, "Через 3 дн.", false)
        SubjectTaskItem("Домашнее задание №3", null, null, false)
    }
}

@Composable
private fun SubjectTaskItem(
    title: String,
    description: String? = null,
    deadline: String? = null,
    completed: Boolean = false
) {
    var isCompleted by remember { mutableStateOf(completed) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                onClick = { isCompleted = !isCompleted }
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(
            if (isCompleted)
                MaterialTheme.colorScheme.surfaceVariant
            else
                MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier
                .height(dimensionResource(R.dimen.no_description_task_card_height))
                .padding(horizontal = dimensionResource(R.dimen.task_card_inner_padding))
                .fillMaxWidth()
                .then(if (isCompleted) Modifier.alpha(0.8f) else Modifier),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isCompleted)
                    Icons.Outlined.CheckCircle
                else
                    Icons.Outlined.Circle,
                contentDescription = null,
                tint = if (isCompleted)
                    Green
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.task_card_between_content_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.task_card_between_content_padding))
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    textDecoration = if (isCompleted)
                        TextDecoration.LineThrough
                    else
                        null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                    )
                if (description != null || deadline != null) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (description != null) {
                            Text(
                                text = description,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 13.sp,
                            )
                        }

                        if (deadline != null) {
                            Text(
                                stringResource(R.string.deadline, deadline),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun TaskBoxLightPreview() {
    StudyTasksTheme {
        TaskBox()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskBoxDarkPreview() {
    StudyTasksTheme {
        TaskBox()
    }
}
