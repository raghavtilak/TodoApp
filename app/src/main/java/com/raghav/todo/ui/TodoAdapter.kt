package com.raghav.todo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghav.todo.data.model.Todo
import com.raghav.todo.databinding.TodoItemBinding

class TodoAdapter(private val onDeleteListener: OnDeleteListener) :
    ListAdapter<Todo, TodoAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            todoDesc.text = currentList[holder.adapterPosition].toString()
            todoContainer.setOnLongClickListener {
                currentList.forEachIndexed { index, todo ->
                    Log.d("TAG","LL->$index,${todo.desc}")
                }
                Log.d("TAG","POS->${holder.adapterPosition}")

                onDeleteListener.onDelete(currentList[holder.adapterPosition])
                return@setOnLongClickListener true
            }
        }
    }

    interface OnDeleteListener {
        fun onDelete(todo: Todo)
    }

    companion object {
        val COMPARATOR = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<Todo>() {

            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }

        }
    }

}