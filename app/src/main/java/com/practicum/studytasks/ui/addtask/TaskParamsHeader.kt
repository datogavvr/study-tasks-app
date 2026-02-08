package com.practicum.studytasks.ui.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme

@Composable
internal fun TaskParamsHeader(
    icon: ImageVector? = null,
    title: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_icon_text_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.small_icon_size)),
                tint = MaterialTheme.colorScheme.onBackground
                )
        }
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun TaskParamsHeaderLightPreview() {
    StudyTasksTheme {
        TaskParamsHeader(
            icon = Icons.Default.Description,
            title = "Название"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskParamsHeaderDarkPreview() {
    StudyTasksTheme {
        TaskParamsHeader(
            icon = Icons.Default.Description,
            title = "Название"
        )
    }
}