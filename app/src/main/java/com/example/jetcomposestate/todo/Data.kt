package com.example.jetcomposestate.todo
import androidx.compose.material.icons.Icons
import androidx.annotation.StringRes
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Restore
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetcomposestate.R
import java.util.UUID

data class TodoItem(
    val task : String,
    val icon : TodoIcon = TodoIcon.Default,
    val id : UUID = UUID.randomUUID()
)

enum class TodoIcon(
    val imageVector: ImageVector,
    @StringRes val contentDescription: Int
) {
    //使用Material Design图标
    Square(Icons.Default.CropSquare, R.string.cd_expand),
    Done(Icons.Default.Done,R.string.cd_done),
    Event(Icons.Default.Event,R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip,R.string.cd_privacy),
    Restore(Icons.Default.Restore,R.string.cd_restore);

    companion object{
        val Default = Square
    }

}
