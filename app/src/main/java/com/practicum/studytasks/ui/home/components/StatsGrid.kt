package com.practicum.studytasks.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.Blue
import com.practicum.studytasks.ui.theme.Green
import com.practicum.studytasks.ui.theme.Peach
import com.practicum.studytasks.ui.theme.Purple

@Composable
fun StatsGrid(
    countTasks: Int,
    countDoneTasks: Int,
    countInProgressTasks: Int,
    countSubjects: Int
) {
    Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.between_cards_padding))) {
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.between_cards_padding))) {
            StatCard(
                title = stringResource(R.string.total_tasks),
                value = countTasks.toString(),
                color = Blue,
                icon = Icons.Default.Task,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.completed_tasks),
                value = countDoneTasks.toString(),
                color = Green,
                icon = Icons.Default.TaskAlt, // CheckCirlcleOutline
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.between_cards_padding))) {
            StatCard(
                title = stringResource(R.string.uncompleted_tasks),
                value = countInProgressTasks.toString(),
                color = Peach,
                icon = Icons.Default.PendingActions,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.total_subjects),
                value = countSubjects.toString(),
                color = Purple,
                icon = Icons.AutoMirrored.Filled.MenuBook,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    color: Color,
    icon: ImageVector,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .height(dimensionResource(R.dimen.stat_card_height))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner))
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.stat_card_content_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_icon_text_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.small_icon_background_size))
                    .background(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size))
                )
            }

            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showSystemUi = true, locale = "ru")
@Composable
fun StatsGridPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.25f))
        StatsGrid(17, 5, 12, 8)
        Spacer(modifier = Modifier.weight(0.75f))
    }
}