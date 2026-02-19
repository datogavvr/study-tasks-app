package com.practicum.studytasks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.studytasks.data.database.dao.FileDao
import com.practicum.studytasks.data.database.dao.SemesterDao
import com.practicum.studytasks.data.database.dao.SubjectDao
import com.practicum.studytasks.data.database.dao.TaskDao
import com.practicum.studytasks.data.database.dao.TaskGroupDao
import com.practicum.studytasks.data.database.entity.TaskEntity
import com.practicum.studytasks.data.database.entity.TaskGroupEntity
import com.practicum.studytasks.data.database.entity.SubjectEntity
import com.practicum.studytasks.data.database.entity.SemesterEntity
import com.practicum.studytasks.data.database.entity.SemesterSubjectCrossRef
import com.practicum.studytasks.data.database.entity.FileEntity
import com.practicum.studytasks.data.database.entity.TaskFileCrossRef

@Database(
    entities = [
        TaskEntity::class,
        TaskGroupEntity::class,
        SubjectEntity::class,
        SemesterEntity::class,
        SemesterSubjectCrossRef::class,
        FileEntity::class,
        TaskFileCrossRef::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TaskDao(): TaskDao
    abstract fun TaskGroupDao(): TaskGroupDao
    abstract fun SubjectDao(): SubjectDao
    abstract fun SemesterDao(): SemesterDao
    abstract fun FileDao(): FileDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}