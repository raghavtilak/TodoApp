package com.raghav.todo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raghav.todo.data.model.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(value = [TodoTypeConverter::class])
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context, TodoDatabase::class.java, "todoDB")
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}