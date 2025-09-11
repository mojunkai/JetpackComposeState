package com.example.jetcomposestate.todo.one

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetcomposestate.todo.TodoItem
import com.example.jetcomposestate.util.generateRandomTodoItem
import kotlin.random.Random

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem : (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit

) {
    Column {
        LazyColumn (
            modifier = Modifier
                .padding(top = 20.dp)
                .weight(1f),
            contentPadding = PaddingValues(top = 8.dp),
            ){
            items(items) {it ->
                val iconAlpha = remember( it.id) { randomTint() }
                TodoRow(todo = it, Modifier.fillParentMaxWidth(), onItemClicked = { onRemoveItem(it)}, iconAlpha)
            }
        }
        Button(
            modifier = Modifier
                .padding(bottom = 40.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            onClick = { onAddItem(generateRandomTodoItem())

            }
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun TodoRow(
    todo : TodoItem,
    modifier: Modifier = Modifier,
    onItemClicked : (TodoItem) -> Unit,
    iconAlpha: Float
){
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable{onItemClicked(todo)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = todo.task)
//        val iconAlpha = remember( todo.id) { randomTint() }
        Icon(
            imageVector = todo.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = stringResource(id = todo.icon.contentDescription)
        )
    }
}

private fun randomTint():Float{
    return Random.nextFloat().coerceIn(0.3f,0.9f)
}