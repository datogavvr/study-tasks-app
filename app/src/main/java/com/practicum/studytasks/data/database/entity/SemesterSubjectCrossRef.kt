package com.practicum.studytasks.data.database.entity

import androidx.room.Entity

@Entity(
    tableName = "semester_has_subject",
    primaryKeys = ["semesterId", "subjectId"]
)
data class SemesterSubjectCrossRef(
    val semesterId: Long,
    val subjectId: Long,
)