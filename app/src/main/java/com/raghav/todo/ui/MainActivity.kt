package com.raghav.todo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.raghav.todo.data.db.TodoDatabase
import com.raghav.todo.data.model.Todo
import com.raghav.todo.data.repository.TodoRepository
import com.raghav.todo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnDeleteListener {

    private lateinit var binding: ActivityMainBinding
    private var calendar: Calendar? = null
    private lateinit var viewmodel: TodoViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoAdapter(this)
        val database = TodoDatabase.getInstance(this)
        val dao = database.todoDao()
        val repository = TodoRepository(dao)

        viewmodel =
            ViewModelProvider(this, ViewmodelFactory(repository)).get(TodoViewmodel::class.java)

        viewmodel.todos.observe(this) {
            adapter.submitList(it)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.pickDateBtn.setOnClickListener {

            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select deadline")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            datePicker.addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar?.timeInMillis = it;
            }
            datePicker.show(supportFragmentManager,"TAG")
        }
        binding.addBtn.setOnClickListener {
            if (calendar == null) {
                Toast.makeText(this, "Select a Deadline", Toast.LENGTH_SHORT).show()
            } else {
                val todo = Todo(binding.todoET.text.toString(), calendar!!, 0)
                viewmodel.addTodo(todo)
            }
        }

    }

    override fun onDelete(todo: Todo) {
        viewmodel.deleteTodo(todo)
    }
}