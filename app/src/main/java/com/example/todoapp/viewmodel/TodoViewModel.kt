package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.Todo
import com.example.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject constructor(private val repository: TodoRepository) :ViewModel(){
    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }
    val getAllToDos = repository.getAllToDos().asLiveData()
}