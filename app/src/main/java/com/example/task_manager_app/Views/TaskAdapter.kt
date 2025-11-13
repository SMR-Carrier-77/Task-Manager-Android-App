package com.example.task_manager_app.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_manager_app.databinding.ItemTaskBinding
import com.example.task_manager_app.db.Task

class TaskAdapter(val listener: HandleTaskClick, val list: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    interface HandleTaskClick {
        fun onEditClick(task: Task)
        fun onLongDeleteClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskVH(binding)
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = list[position]
        holder.binding.apply {
            tvTitle.text = task.title
            tvDescription.text = task.description
            tvDueDate.text = "Due: ${task.dueDate ?: "N/A"}"
            cbCompleted.isChecked = task.isCompleted

            btnEdit.setOnClickListener {
                listener.onEditClick(task)
            }
            btnDelete.setOnClickListener {
                listener.onLongDeleteClick(task)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class TaskVH(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}
