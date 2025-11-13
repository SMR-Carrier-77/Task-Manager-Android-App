package com.example.task_manager_app.db

import androidx.room.Database
import androidx.room.RoomDatabase

class TaskDatabase {


    @Database(entities = [Task::class], version = 1)
    abstract class TaskDatabase: RoomDatabase() {
        abstract fun getTaskDao(): TaskDao

    }
}