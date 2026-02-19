package com.practicum.studytasks.domain.model

data class Semester(
    val id: Long = 0,
    val number: String,
    val startDate: String?,
    val endDate: String?
)