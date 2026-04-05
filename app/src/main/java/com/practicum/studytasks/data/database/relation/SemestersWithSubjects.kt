package com.practicum.studytasks.data.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.practicum.studytasks.data.database.entity.SemesterEntity
import com.practicum.studytasks.data.database.entity.SemesterSubjectCrossRef
import com.practicum.studytasks.data.database.entity.SubjectEntity

data class SemestersWithSubjects(
    @Embedded val semester: SemesterEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            SemesterSubjectCrossRef::class,
            parentColumn = "semesterId",
            entityColumn = "subjectId"
        )
    )
    val subject: List<SubjectEntity>
)