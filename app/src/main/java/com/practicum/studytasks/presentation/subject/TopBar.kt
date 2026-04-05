package com.practicum.studytasks.presentation.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.presentation.theme.StudyTasksTheme

@Composable
internal fun TopBar(
    onBack: () -> Unit = {}
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .height(dimensionResource(R.dimen.bottom_bar_height))
    ) {
        Box(
            Modifier
                .size(dimensionResource(R.dimen.add_task_button_size))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = onBack
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size)),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showSystemUi = true, locale = "ru")
@Composable
private fun TopBarLightPreview() {
    StudyTasksTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            TopBar()
            Spacer(modifier = Modifier.weight(0.9f))
        }
    }
}

@Preview(showSystemUi = true, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TopBarDarkPreview() {
    StudyTasksTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            TopBar()
            Spacer(modifier = Modifier.weight(0.9f))
        }
    }
}