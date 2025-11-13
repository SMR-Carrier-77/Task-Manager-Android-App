package com.example.task_manager_app.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.task_manager_app.databinding.ActivityMainBinding
import com.example.task_manager_app.db.Task
import com.example.task_manager_app.db.TaskDao
import com.example.task_manager_app.db.TaskDatabase

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), TaskAdapter.HandleTaskClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: TaskDao
    private lateinit var adapter: TaskAdapter
    private var taskList = listOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "Task_DB"
        ).allowMainThreadQueries().build()

            dao = it.getTaskDao()


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        showTasks()

        binding.btn2.setOnClickListener {
            val i = Intent(this, AddTaskActivity::class.java)
            startActivity(i)
        }
    }

    private fun showTasks() {
        taskList = dao.getAllTask()
        adapter = TaskAdapter(this, taskList)
        binding.recyclerView.adapter = adapter
    }

    override fun onEditClick(task: Task) {
        val i = Intent(this, AddTaskActivity::class.java)
        i.putExtra(AddTaskActivity.EDIT_KEY, task)
        startActivity(i)
    }

    override fun onLongDeleteClick(task: Task) {
        dao.deleteTask(task)
        showTasks()
    }

    override fun onResume() {
        super.onResume()
        showTasks()
    }
}
