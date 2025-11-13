package com.example.task_manager_app.Views

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.UiContext
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.task_manager_app.R
import com.example.task_manager_app.databinding.ItemTaskBinding
import com.example.task_manager_app.db.Task
import java.util.Date
import java.util.Locale

class TaskAdapter(val listener: HandleTaskClick, var context: Context, val list: MutableList<Task>) :
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
            cbCompleted.isChecked = task.isCompleted

            var dueDate = task.dueDate.toLong()

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = formatter.format(Date(dueDate))

            tvDueDate.text = formattedDate

            btnEdit.setOnClickListener {
                listener.onEditClick(task)
            }
            btnDelete.setOnClickListener {

                AlertDialog.Builder(context)
                    .setTitle("Warning")
                    .setMessage("Are you sure you want to delete this task?")
                    .setPositiveButton("Yes"){dialog,_ ->
                        listener.onLongDeleteClick(task)
                    }
                    .setNegativeButton("No"){dialog,_ ->
                        dialog.dismiss()
                    }
                    .show()

            }
        }
    }

    override fun getItemCount(): Int = list.size

    class TaskVH(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}