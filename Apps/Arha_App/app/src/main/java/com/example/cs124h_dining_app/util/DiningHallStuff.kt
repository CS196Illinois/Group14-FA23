package com.example.cs124h_dining_app.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun date(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
    return current.format(formatter)
}

fun meal(): String {
    val current = LocalDateTime.now()
    val hour = current.format(DateTimeFormatter.ofPattern(("HH"))).toInt()
    return if (hour > 14) {
        "Dinner"
    } else if (hour > 10) {
        "Lunch"
    } else {
        "Breakfast"
    }
}

fun hallName(id: String) = when (id) {
    "1" -> "Ikenberry"
    "2" -> "PAR"
    "3" -> "ISR"
    "5" -> "LAR"
    else -> ""
}