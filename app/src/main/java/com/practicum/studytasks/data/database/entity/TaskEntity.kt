package com.practicum.studytasks.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val taskTypeId: String? = null,
    val subjectId: String? = null,
    val deadline: String? = null,
    val description: String? = null,
    val isCompleted: Boolean = false
    )
