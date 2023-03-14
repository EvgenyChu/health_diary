package ru.churkin.health_diary.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.churkin.health_diary.R

@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var visible by remember { mutableStateOf(false) }
LaunchedEffect(key1 = Unit){
    visible = true
}
        Box() {
            Row() {
                Icon(
                    modifier = Modifier.size(212.dp),
                    painter = painterResource(id = R.drawable.baground_icon),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            }
            Column() {
                AnimatedVisibility(
                    visible = visible,
                    enter = expandHorizontally(expandFrom = Alignment.End, animationSpec = tween(2000)),
                    exit = fadeOut(),
                ) {
                    Image(
                        modifier = Modifier.size(210.dp).padding(start = 4.dp),
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = null,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.start_title),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h1
        )
    }
}
