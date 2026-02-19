package com.practicum.studytasks.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "file")
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val uri: String,
    val name: String
)