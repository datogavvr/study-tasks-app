package com.practicum.studytasks.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.Blue

@Composable
fun ProgressBar(
    progress: Int
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = Color.LightGray
        )
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.card_inner_padding))) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_icon_text_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ShowChart,
                    contentDescription = stringResource(R.string.progress),
                    modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size)),
                    tint = Blue
                )
                Text(text = stringResource(R.string.progress), fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.progressbar_header_padding)))
            LinearProgressIndicator(
                progress = { progress/100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.progressbar_line_height)),
                color = Color.Black,
                trackColor = Color.LightGray
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.progressbar_content_padding)))
            Text(text = stringResource(R.string.progress_value, progress), color = Color.Gray)
        }
    }
}

@Preview(showSystemUi = true, locale = "ru")
@Composable
fun ProgressBarPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.25f))
        ProgressBar(29)
        Spacer(modifier = Modifier.weight(0.75f))
    }
}
