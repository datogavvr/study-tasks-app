package com.practicum.studytasks.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "semester")
data class SemesterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val number: String,
    val startDate: String?,
    val endDate: String?
)