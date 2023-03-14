package ru.churkin.health_diary.ui.componentsUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.churkin.health_diary.R

@Composable
fun UserField(
    value: String,
    title: String,
    modifier: Modifier = Modifier,
    onValue: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h5
        )
        TextField(
            value = value,
            shape = RoundedCornerShape(20.dp),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = FontFamily(Font(R.font.zen_maru_gothic_regular)),
                fontSize = 16.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValue
        )
    }
}