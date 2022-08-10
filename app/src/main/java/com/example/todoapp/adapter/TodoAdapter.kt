package com.example.todoapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoLayoutAdapterBinding
import com.example.todoapp.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: TodoLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = differ.currentList[position]
        holder.binding.tvTodoTitle.text = todo.todoTitle

        holder.binding.checkBox.setOnClickListener{
            if (holder.binding.checkBox.isChecked){
                holder.binding.tvTodoTitle.paintFlags =
                    holder.binding.tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            }else{
                holder.binding.tvTodoTitle.paintFlags =
                    holder.binding.tvTodoTitle.paintFlags and  Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    override fun getItemCount() = differ.currentList.size
}