package com.example.jetcomposestate.todo.two

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.jetcomposestate.todo.TodoIcon
import com.example.jetcomposestate.todo.TodoItem


//输入框
@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction : () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
        modifier = modifier,
        //配置软键盘
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        )
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
    icon : TodoIcon,
    onIconChange : (TodoIcon) -> Unit,
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
        ) {
            IconRow(icon = icon, onIconChange = onIconChange)
        }
    }
}

@Composable
fun IconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier
    ) {
    Row(modifier){
        for( todoIcon in TodoIcon.values()){
            SelectableIconButton(
                icon = todoIcon.imageVector,
                iconContentDescription = todoIcon.contentDescription,
                onIconSelecteed = { onIconChange(todoIcon) },
                isSelected = todoIcon == icon,
            )
        }
    }

}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    iconContentDescription: Int,
    onIconSelecteed: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    TextButton(
        onClick = {onIconSelecteed()},
        modifier = modifier,
        shape = CircleShape,
    ) {
        Column {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(iconContentDescription),
                tint = tint
            )
            if(isSelected){
                Box(
                    Modifier
                        .padding(top = 2.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                ){
                }
            }else{
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}


@Composable
fun TodoItemInput(
    onItemComlete: (TodoItem) -> Unit
) {
    val(text, setText) = remember { mutableStateOf("") }
    val(icon,seticon) = remember { mutableStateOf(TodoIcon.Default) }
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComlete(TodoItem(text))
        seticon(TodoIcon.Default)
        setText("")
    }
    Column {
        Row (Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)){
            TodoInputText(
                text = text,
                onTextChange = setText,
                modifier = Modifier.weight(1f),
                onImeAction = submit
            )
            TodoEditButton(
                onClick = submit,
                text = "ADD",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
        if(iconsVisible){
            AnimatedIconRow(
                icon = icon,
                onIconChange = seticon,
                visible = iconsVisible,
                modifier = Modifier.padding(top = 8.dp))
        }
        else{
            Spacer(modifier = Modifier.height(16.dp)   )
        }
    }
}