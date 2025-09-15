package com.example.jetcomposestate.todo.one

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.example.jetcomposestate.todo.TodoItem


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
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(),
        modifier = modifier,
        enabled = enabled
    ) {
        Text( text= text )
    }
}

//根据文本是否有内容，自动收起和弹出
@Composable
fun AnimatedIconRow(
    modifier: Modifier = Modifier,
    visible : Boolean = true
){
    val enter = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutSlowInEasing)) }
    val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }
    Box(
        modifier.defaultMinSize(minHeight = 16.dp)
    ){
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit
        ) { }
    }
}

@Composable
fun TodoItemInput(
    onItemComlete: (TodoItem) -> Unit
) {
    val(text, setText) = remember { mutableStateOf("") }
    Column {
        Row (Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)){
            TodoInputText(
                text = text,
                onTextChange = setText,
                modifier = Modifier.weight(1f)
            )
            TodoEditButton(
                onClick = {
                    onItemComlete(TodoItem(text))
                    setText("")
                },
                text = "ADD",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
    }
}