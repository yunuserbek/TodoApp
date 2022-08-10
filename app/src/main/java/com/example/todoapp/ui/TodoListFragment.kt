package com.example.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentTodoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        binding.floatingActionButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)

        }
    }

    private fun setupRv() {
        todoAdapter = TodoAdapter()
        binding.rvListTodo.adapter = todoAdapter
        binding.rvListTodo.layoutManager =LinearLayoutManager(activity)
        binding.rvListTodo.setHasFixedSize(true)
        viewModel.getAllToDos.observe(viewLifecycleOwner){listTodo->

            updateUi(listTodo)
            todoAdapter.differ.submitList(listTodo)

        }
    }

    private fun updateUi(listTodo: List<Todo>?) {
        if (listTodo!!.isNotEmpty()){
            binding.rvListTodo.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE

        }else{
            binding.rvListTodo.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE

        }
    }


}