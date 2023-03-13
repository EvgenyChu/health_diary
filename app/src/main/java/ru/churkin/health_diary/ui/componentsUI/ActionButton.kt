package ru.churkin.health_diary.ui.componentsUI

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.churkin.health_diary.R

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorBacActive = MaterialTheme.colors.primary
    val colorTint = MaterialTheme.colors.onPrimary
    FloatingActionButton(
        modifier = modifier
            .size(80.dp),
        onClick = onClick,
        interactionSource = MutableInteractionSource(),
        backgroundColor = colorBacActive,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_add_24),
            tint = colorTint,
            contentDescription = "Добавить"
        )
    }
}