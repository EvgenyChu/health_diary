package ru.churkin.health_diary.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.churkin.health_diary.R

@Composable
fun StartScreen(){
    var counter by remember{ mutableStateOf(0) }
    var size by remember{ mutableStateOf(0) }
    LaunchedEffect(key1 = Unit){
            while (size < 210) {
                delay(42)
                counter += 1
                size += 5
            }
    }

Column(
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Box() {
        Row() {
            Icon(
                modifier = Modifier.size(212.dp),
                painter = painterResource(id = R.drawable.baground_icon),
                tint = Color.Unspecified,
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .width(size.dp)
                .height(212.dp)
                .align(Alignment.Center)
        ) {
            Icon(
               modifier = Modifier.size(210.dp),
               painter = painterResource(id = R.drawable.heart_icon),
               tint = Color.Unspecified,
               contentDescription = null
            )
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