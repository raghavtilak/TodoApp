package com.raghav.todo.data.db

import androidx.room.TypeConverter
import java.util.*

class TodoTypeConverter {

    @TypeConverter
    fun dateToLong(date: Calendar):Long{
        return date.timeInMillis
    }

    @TypeConverter
    fun longToDate(long:Long):Calendar{
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = long;
        return calendar
    }
}