package com.practicum.studytasks.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = White,

    secondary = VeryDarkGray,

    tertiary = Gray,

    background = Black,
    onBackground = White,

    surface = VeryDarkGray,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = Gray,

    error = Red,
    onError = White,

    outline = DarkGray,
    outlineVariant = Gray,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,

    secondary = White,

    tertiary = Gray,

    background = LightGray,
    onBackground = Black,

    surface = White,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = DarkGray,

    error = Red,
    onError = White,

    outline = Gray,
    outlineVariant = DarkGray,
)

@Composable
fun StudyTasksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}