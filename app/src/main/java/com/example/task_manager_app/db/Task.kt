package com.example.task_manager_app.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Task(
@PrimaryKey(autoGenerate = true)
val id: Int = 0,
val title: String,
val description: String,
val dueDate: String,
val isCompleted: Boolean
): Parcelable


