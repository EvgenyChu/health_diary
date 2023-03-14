package ru.churkin.health_diary.ui.componentsUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textColor: Color = MaterialTheme.colors.onPrimary,
    onClick: () -> Unit
){
    Button(
        shape = RoundedCornerShape(100.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        enabled = isEnabled,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .height(46.dp)
                .then(
                    if (isEnabled) {
                        Modifier.background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(100.dp)
                        )
                    } else {
                        Modifier.background(
                            color = MaterialTheme.colors.error,
                            shape = RoundedCornerShape(100.dp)
                        )
                    }
                )
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
             text = text.uppercase(),
             modifier = Modifier.padding(horizontal = 8.dp),
             textAlign = TextAlign.Center,
             letterSpacing = 1.1.sp,
             style = MaterialTheme.typography.h1,
             fontSize = 13.sp,
             color = textColor
         )
        }
    }
}