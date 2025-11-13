package com.example.task_manager_app.Views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.task_manager_app.databinding.ActivityAddTaskBinding
import com.example.task_manager_app.db.Task
import com.example.task_manager_app.db.TaskDao
import com.example.task_manager_app.db.TaskDatabase


@Suppress("DEPRECATION")
class AddTaskActivity : AppCompatActivity() {

    companion object {
        const val EDIT_KEY = "edit_task"
        const val UPDATE_TEXT = "Update Task"
        const val ADD_TEXT = "Add Task"
    }

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var dao: TaskDao
    private var taskId = 0
    private var selectedDate = ""
    private var isCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Database setup
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "Task_DB"
        ).allowMainThreadQueries().build()

        dao = db.getTaskDao()

        // Toggle DatePicker visibility
        binding.tvDueDateValue.setOnClickListener {
            binding.datePicker.visibility =
                if (binding.datePicker.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        // Handle date picker change
        binding.datePicker.setOnDateChangedListener { _: DatePicker, year, month, day ->
            selectedDate = "$day/${month + 1}/$year"
            binding.tvDueDateValue.text = selectedDate
        }

        // Check if Edit mode
        if (intent.hasExtra(EDIT_KEY)) {
            val task = intent.getParcelableExtra<Task>(EDIT_KEY)
            binding.btnSaveTask.text = UPDATE_TEXT

            task?.let {
                binding.apply {
                    etTitle.setText(it.title)
                    etDescription.setText(it.description)
                    tvDueDateValue.text = it.dueDate ?: "Select Date"
                    cbIsCompleted.isChecked = it.isCompleted
                    taskId = it.id
                }
                selectedDate = task?.dueDate ?: ""
                isCompleted = task?.isCompleted ?: false
            }

        } else {
            binding.btnSaveTask.text = ADD_TEXT
        }

        // Checkbox listener
        binding.cbIsCompleted.setOnCheckedChangeListener { _, checked ->
            isCompleted = checked
        }

        // Save or Update button click
        binding.btnSaveTask.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                binding.etTitle.error = "Enter title"
                return@setOnClickListener
            }

            if (binding.btnSaveTask.text.toString() == ADD_TEXT) {
                addTask(title, description)
            } else {
                updateTask(title, description)
            }
        }
    }

    private fun updateTask(title: String, description: String) {
        val task = Task(taskId, title, description, selectedDate, isCompleted)
        dao.updateTask(task)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun addTask(title: String, description: String) {
        val task = Task(0, title, description, selectedDate, isCompleted)
        dao.addTask(task)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
