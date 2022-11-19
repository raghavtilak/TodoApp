package com.raghav.todo.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raghav.todo.util.day
import com.raghav.todo.util.month
import java.util.*

@Entity(tableName = "todo", indices = [Index(value = ["desc", "date"], unique = true)])
data class Todo(
    val desc: String,
    val date: Calendar,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString(): String {
        return "$desc\n${date.day()} ${date.get(Calendar.DATE)} ${date.month()}"
    }
}