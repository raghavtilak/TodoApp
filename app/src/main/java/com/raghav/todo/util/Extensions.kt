package com.raghav.todo.util

import java.util.*
import java.util.Calendar.*

fun Calendar.day() = when (get(DAY_OF_WEEK)) {
    MONDAY -> "Monday"
    TUESDAY -> "Tuesday"
    WEDNESDAY -> "Wednesday"
    THURSDAY -> "Thursday"
    FRIDAY -> "Friday"
    SATURDAY -> "Saturday"
    else -> "Sunday"
}

fun Calendar.month() = when (get(MONTH)) {
    JANUARY -> "Jan"
    FEBRUARY -> "Feb"
    MARCH -> "Mar"
    APRIL -> "Apr"
    MAY -> "May"
    JUNE -> "Jun"
    JULY -> "Jul"
    AUGUST -> "Aug"
    SEPTEMBER -> "Sept"
    OCTOBER -> "Oct"
    NOVEMBER -> "Nov"
    else -> "Dec"
}