package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.practicum.studytasks.data.database.entity.SubjectEntity

@Dao
interface SubjectDao {
    @Insert
    suspend fun insertSubject(subject: SubjectEntity)
}