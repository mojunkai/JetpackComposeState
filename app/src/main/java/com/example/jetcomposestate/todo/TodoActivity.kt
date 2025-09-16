package com.example.jetcomposestate.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetcomposestate.todo.one.TodoViewModle
import com.example.jetcomposestate.todo.three.TodoScreen
import com.example.jetcomposestate.ui.theme.JetComposeStateTheme

class TodoActivity : ComponentActivity() {

    private val todoViewModle by viewModels<TodoViewModle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetComposeStateTheme {
                TodoActivityScreen()
            }
        }
    }

    @Composable
    private fun TodoActivityScreen() {
//        val items = listOf(
//            TodoItem("Learn compose", TodoIcon.Event),
//            TodoItem("Build an app", TodoIcon.Done),
//            TodoItem("Find a job", TodoIcon.Square),
//            TodoItem("???", TodoIcon.Square),
//            TodoItem("PROFIT", TodoIcon.Square)
//        )
//        TodoScreen(items)

        val items: List<TodoItem> by todoViewModle.todoItems.observeAsState(listOf())

        TodoScreen(
            items = items,
            onAddItem = { todoViewModle.addItem(it) },
            onRemoveItem = { todoViewModle.removeItem(it) })
    }
}