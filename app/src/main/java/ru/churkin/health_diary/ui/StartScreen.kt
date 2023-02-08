package ru.churkin.health_diary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.churkin.health_diary.R

@Composable
fun StartScreen(){
Column(
    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Icon(
        modifier = Modifier.size(112.dp),
        painter = painterResource(id = R.drawable.ic_round_heart_broken_24),
        tint = MaterialTheme.colors.secondary,
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = R.string.start_title),
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h1
    )
}
}