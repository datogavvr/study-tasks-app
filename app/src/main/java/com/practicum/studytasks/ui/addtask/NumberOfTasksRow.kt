package com.practicum.studytasks.ui.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import com.practicum.studytasks.ui.ui_components.CustomTextField

@Composable
internal fun NumberOfTasksRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.input_field_height)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.task_numbers),
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.input_field_height))
                    .aspectRatio(6f / 5f),
                placeholder = stringResource(R.string.from)
            )

            Text(
                text = stringResource(R.string.dash),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.between_cards_padding)),
                color = MaterialTheme.colorScheme.onSurface
            )

            CustomTextField(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.input_field_height))
                    .aspectRatio(6f / 5f),
                placeholder = stringResource(R.string.to)
            )
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun NumberOfTasksLightPreview() {
    StudyTasksTheme {
        NumberOfTasksRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NumberOfTasksDarkPreview() {
    StudyTasksTheme {
        NumberOfTasksRow()
    }
}
