package com.practicum.studytasks.data.database.entity

import androidx.room.Entity

@Entity(
    tableName = "task_has_file",
    primaryKeys = ["taskId", "fileId"]
)
data class TaskFileCrossRef(
    val taskId: Long,
    val fileId: Long,
)