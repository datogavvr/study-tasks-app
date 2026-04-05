package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicum.studytasks.data.database.entity.TaskGroupEntity

@Dao
interface TaskGroupDao {
    @Insert
    suspend fun insertTaskGroup(taskGroup: TaskGroupEntity)

    @Query("SELECT * FROM task_group")
    fun getTaskGroups(): List<TaskGroupEntity>
}