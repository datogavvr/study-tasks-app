package com.practicum.studytasks.presentation.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.R
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
internal fun SubjectHeader(
    title: String,
    teacher: String,
    classroom: String,
    icon: ImageVector = Icons.AutoMirrored.Filled.MenuBook,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.screen_inner_padding))
            .padding(top = dimensionResource(R.dimen.content_blocks_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_icon_text_padding)),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.large_icon_background_size))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(dimensionResource(R.dimen.large_icon_size)),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column {
            Text(title, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text(teacher, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
            Text(classroom, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)

        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun SubjectHeaderLightPreview() {
    StudyTasksTheme {
        SubjectHeader("Вайбкодинг", "Месси Л.А.", "Г-307-1")
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SubjectHeaderDarkPreview() {
    StudyTasksTheme {
        SubjectHeader("Вайбкодинг", "Месси Л.А.", "Г-307-1")
    }
}