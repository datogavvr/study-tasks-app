package com.practicum.studytasks.ui.addtask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
fun CustomDropdown(
    initValue: String = stringResource(R.string.not_selected_variant),
    value: String = initValue,
    onValueChange: (String) -> Unit = {},
    items: List<String> = listOf("Домашнее задание", "Контрольная", "Лабораторная")
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
            .clickable {
                focusManager.clearFocus()
                expanded = !expanded
                       },
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Column {
            Row(
                modifier = Modifier.padding(dimensionResource(R.dimen.input_field_inner_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    Modifier.weight(1f),
                    color = if (value == stringResource(R.string.not_selected_variant))
                        MaterialTheme.colorScheme.onSurfaceVariant
                    else
                        MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = if (expanded)
                        Icons.Default.ArrowDropUp
                    else
                        Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.select_variant),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            AnimatedVisibility(visible = expanded) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                Column {
                    items.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = dimensionResource(R.dimen.dropdown_horizontal_padding),
                                    vertical = dimensionResource(R.dimen.dropdown_vertical_padding)
                                )
                                .clickable {
                                    onValueChange(item)
                                    expanded = false
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomDropdownLightPreview() {
    StudyTasksTheme {
        CustomDropdown()
    }
}

@Preview(showBackground = true, backgroundColor = 0, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CustomDropdownDarkPreview() {
    StudyTasksTheme {
        CustomDropdown()
    }
}