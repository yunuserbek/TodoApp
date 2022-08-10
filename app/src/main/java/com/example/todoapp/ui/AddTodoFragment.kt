package com.example.todoapp.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : Fragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        }
        binding.btnAddTodo.setOnClickListener { mView ->

            saveTodo(mView)
        }
    }

    private fun saveTodo(mView: View?) {
        val todoName = binding.etTodoTitle.text.toString()
        if (todoName.isNotEmpty()) {
            val todo = Todo(0, todoName)
            viewModel.insertTodo(todo)
            Snackbar.make(mView!!, "todo added", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        } else{
            val toast = Toast.makeText(
                activity,
                "please enter title",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        }
    }



}