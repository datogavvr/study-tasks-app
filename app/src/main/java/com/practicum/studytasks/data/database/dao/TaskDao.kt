package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.practicum.studytasks.data.database.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: TaskEntity)

}
