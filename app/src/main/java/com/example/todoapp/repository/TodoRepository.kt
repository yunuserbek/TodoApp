package com.example.todoapp.repository

import com.example.todoapp.db.TodoDao
import com.example.todoapp.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: TodoDao){

    suspend fun insertTodo(todo: Todo) = dao.insertTodo(todo)

    fun getAllToDos() = dao.getAllTodos()
}