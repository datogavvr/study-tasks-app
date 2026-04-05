package com.practicum.studytasks.data.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.practicum.studytasks.data.database.entity.FileEntity
import com.practicum.studytasks.data.database.entity.TaskEntity
import com.practicum.studytasks.data.database.entity.TaskFileCrossRef

data class TasksWithFiles(
    @Embedded val task: TaskEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            TaskFileCrossRef::class,
            parentColumn = "taskId",
            entityColumn = "fileId"
        )
    )
    val file: List<FileEntity>
)