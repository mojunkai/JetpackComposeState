package com.example.jetcomposestate.util

import com.example.jetcomposestate.todo.TodoIcon
import com.example.jetcomposestate.todo.TodoItem

fun generateRandomTodoItem(): TodoItem {
    val message = listOf(
        "Study",
        "Workout",
        "Eat",
        "Sleep",
        "Code",
        "Repeat",
        "Build dynamic UIs"
    ).random()
    val icon = TodoIcon.values().random()
    return TodoItem(message, icon)
}