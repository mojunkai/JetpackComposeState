package com.example.jetcomposestate.todo.one

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetcomposestate.todo.TodoItem
import androidx.compose.material3.TextFieldColors



//输入框
@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            // 设置文本字段聚焦时的背景颜色
            focusedContainerColor =Color.Transparent,

            // 设置文本字段未聚焦或初始状态时的背景颜色
            unfocusedContainerColor = Color.Transparent,

            // 设置文本字段禁用时的背景颜色
            disabledContainerColor = Color.Transparent,
        ),
        maxLines = 1,
        modifier = modifier
    )
}

//编辑按钮
@Composable
fun TodoEditButton(
    text: String,
    onClick: () -> Unit
) {
}

@Composable
fun TodoItemInput(
    onItemComlete: (TodoItem) -> Unit
) {
    Column {
        Row (Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)){

        }
    }
}