package com.practicum.studytasks.ui.addtask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomDatePicker(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }

    // create today's date
    val calendar = Calendar.getInstance()
    val todayMillis = calendar.timeInMillis

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = if (selectedDate.isNotEmpty()) {
            dateToMillis(selectedDate)
        } else todayMillis + 10000000, // понять, почему не ставится норм время
        initialDisplayMode = DisplayMode.Picker,
        yearRange = IntRange(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.YEAR) + 5
        )
    )

    val formattedDate = if (selectedDate.isNotEmpty()) {
        formatDate(selectedDate)
    } else stringResource(R.string.select_date)

    Box(modifier = modifier) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            border = BorderStroke(
                width = dimensionResource(R.dimen.card_border_width),
                color = MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.input_field_inner_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formattedDate,
                    color = if (selectedDate.isEmpty())
                        MaterialTheme.colorScheme.onSurfaceVariant
                    else
                        MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.select_date),
                    tint = MaterialTheme.colorScheme.onSurface,                )
            }
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val newDate = millisToDate(millis)
                                onDateSelected(newDate)
                            }
                            showDatePicker = false
                        }
                    ) { Text(
                        text = stringResource(R.string.save),
                        color = MaterialTheme.colorScheme.primary
                        ) }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Box(
                    Modifier.fillMaxSize()
                ) {
                    DatePicker(
                        state = datePickerState,
                        colors = DatePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                            dividerColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }

        }
    }
}

private fun dateToMillis(dateString: String): Long? {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.parse(dateString)?.time
}

private fun millisToDate(millis: Long): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(Date(millis))
}

private fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date ?: Date())
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun CustomDatePickerLightPreview() {
    StudyTasksTheme {
        CustomDatePicker(
            selectedDate = "",
            onDateSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, locale = "ru")
@Composable
private fun CustomDatePickerDarkPreview() {
    StudyTasksTheme {
        CustomDatePicker(
            selectedDate = "",
            onDateSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

