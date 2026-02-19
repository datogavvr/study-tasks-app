package com.practicum.studytasks.domain.model

data class Task(
    val id: Long = 0,
    val name: String,
    val taskTypeId: String? = null,
    val subjectId: String? = null,
    val deadline: String? = null,
    val files: List<File> = emptyList(),
    val description: String? = null,
    val isCompleted: Boolean = false
)