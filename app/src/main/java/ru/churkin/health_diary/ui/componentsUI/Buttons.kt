package ru.churkin.health_diary.ui.componentsUI

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EnterButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        shape = RoundedCornerShape(100.dp),
        modifier = modifier,
        onClick = onClick) {

    }
}