package com.practicum.studytasks.domain.model

data class Subject(
    val id: Long = 0,
    val name: String,
    val color: Int?,
    val teacher: String?,
    val classroom: String?
)