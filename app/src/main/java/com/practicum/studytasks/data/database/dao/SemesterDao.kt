package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicum.studytasks.data.database.entity.SemesterEntity

@Dao
interface SemesterDao {
    @Insert
    suspend fun insertSemester(semester: SemesterEntity)

    @Query("SELECT * FROM semester")
    fun getSemesters(): List<SemesterEntity>
}