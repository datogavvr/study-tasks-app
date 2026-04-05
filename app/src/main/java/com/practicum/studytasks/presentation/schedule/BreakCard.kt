package com.practicum.studytasks.presentation.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.studytasks.R
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
internal fun BreakCard() {
    Card(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner))),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row( // TODO: внедрить данные с БД
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Перерыв",
                modifier = Modifier.Companion.padding(12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "20 мин",
                modifier = Modifier.Companion.padding(12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun BreakCardLightPreview() {
    StudyTasksTheme {
        BreakCard()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun BreakCardDarkPreview() {
    StudyTasksTheme {
        BreakCard()
    }
}