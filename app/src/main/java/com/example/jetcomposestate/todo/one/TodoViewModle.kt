package com.example.jetcomposestate.todo.one

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetcomposestate.todo.TodoItem

class TodoViewModle: ViewModel() {
    private var _todoItems = MutableLiveData(mutableListOf<TodoItem>())

    val todoItems = _todoItems

    fun addItem(item: TodoItem) {
        _todoItems.value = _todoItems.value?.toMutableList()?.apply{add(item)}
    }
    fun removeItem(item: TodoItem) {
        _todoItems.value = _todoItems.value?.toMutableList()?.apply { remove(item) }
    }
}