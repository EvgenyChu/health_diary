package ru.churkin.health_diary.ui.componentsUI

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterButton(
    @StringRes text: Int,
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
                .height(56.dp)
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
             text = stringResource(id = text).uppercase(),
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

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    iconPainter: Int,
    iconTint: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .width(56.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            )
            .clip(RoundedCornerShape(50.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconPainter),
            tint = iconTint,
            contentDescription = null
        )
    }
}