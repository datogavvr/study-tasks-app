package com.practicum.studytasks.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.Blue

@Composable
fun BottomBar() {
    Box {
        BottomAppBar(
            containerColor = Color.White,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.bottom_bar_height)),
                verticalAlignment = Alignment.Bottom
            ) {
                // left side
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BottomItem(Icons.Default.Home, stringResource(R.string.home))
                    BottomItem(Icons.Default.DateRange, stringResource(R.string.schedule))
                }

                // add task button
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.add_task_button_size))
                        .clip(shape = CircleShape)
                        .background(color = Blue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Add,
                        stringResource(R.string.add_task),
                        tint = Color.White
                    )
                }

                // right side
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BottomItem(Icons.AutoMirrored.Filled.MenuBook, stringResource(R.string.subjects))
                    BottomItem(Icons.AutoMirrored.Filled.List, stringResource(R.string.tasks))
                }
            }
        }
    }
}

@Composable
private fun BottomItem(icon: ImageVector, label: String) {
    Column(
        Modifier
            .size(dimensionResource(R.dimen.add_task_button_size))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)))
            .clickable { },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(icon, label, Modifier.size(dimensionResource(R.dimen.small_icon_size)))
     //   Text(label, fontSize = 12.sp)
    }
}

@Preview(showSystemUi = true, locale = "ru")
@Composable
fun BottomBarPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        BottomBar()
    }
}