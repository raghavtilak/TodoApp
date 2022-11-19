package com.raghav.todo.ui

import androidx.lifecycle.*
import com.raghav.todo.data.model.Todo
import com.raghav.todo.data.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewmodel(private val repository: TodoRepository) : ViewModel() {

    val todos : LiveData<List<Todo>>
    get() = repository.todos

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }



}
class ViewmodelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewmodel(repository) as T
    }
}