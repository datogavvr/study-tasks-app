package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.practicum.studytasks.data.database.entity.FileEntity

@Dao
interface FileDao {
    @Insert
    suspend fun insertFile(file: FileEntity)
}