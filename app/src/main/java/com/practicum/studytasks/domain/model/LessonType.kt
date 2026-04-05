package com.practicum.studytasks.domain.model

import androidx.compose.ui.graphics.Color
import com.practicum.studytasks.presentation.theme.Blue
import com.practicum.studytasks.presentation.theme.Gray
import com.practicum.studytasks.presentation.theme.LightBlue
import com.practicum.studytasks.presentation.theme.Peach

enum class LessonType(val color: Color, val label: String) {
    LECTURE(Blue, "Лекция"),
    LAB(LightBlue, "Лабораторная"),
    PRACTICE(Peach, "Практика"),
    BREAK(Gray, "Перерыв")
}