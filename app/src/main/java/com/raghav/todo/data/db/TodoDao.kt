package com.raghav.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.raghav.todo.data.model.Todo

@Dao
interface TodoDao {

    //use this in repository pattern (access through repository)
    @Query("Select * from todo")
    fun getTodos():LiveData<List<Todo>>

    //use this in normal viewmodel pattern (access through viewmodel)
//    @Query("Select * from todo")
//    suspend fun getTodos():List<Todo>

    @Insert(onConflict = IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)


}