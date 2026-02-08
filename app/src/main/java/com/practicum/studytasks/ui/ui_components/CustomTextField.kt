package com.practicum.studytasks.ui.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
fun CustomTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true,
) {
    var textValue by remember { mutableStateOf(value) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner))),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        TextField(
            value = textValue,
            onValueChange = {
                onValueChange(it)
                textValue = it.trimStart()
                            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
            singleLine = singleLine,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(0f),
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(0f),
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun CustomTextfieldLightPreview() {
    StudyTasksTheme {
        CustomTextField(
            placeholder = "Placeholder"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CustomTextfieldDarkPreview() {
    StudyTasksTheme {
        CustomTextField(
            placeholder = "Placeholder"
        )
    }
}
