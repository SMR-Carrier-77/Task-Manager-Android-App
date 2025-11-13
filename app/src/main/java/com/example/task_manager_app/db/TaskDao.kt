package com.example.task_manager_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

class TaskDao {



    @Dao
    interface TaskDao {
        @Insert
        fun addTask(task: TaskDao)

        @Update
        fun editTask(task: TaskDao)

        @Delete
        fun deleteTask(task: TaskDao)

        @Query("SELECT*FROM Task")
        fun getAllTask(): List<Task>

    }
}