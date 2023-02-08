package ru.churkin.health_diary.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.churkin.health_diary.R

val regular = FontFamily(Font(R.font.zen_maru_gothic_regular))
val bold = FontFamily(Font(R.font.zen_maru_gothic_bold))

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = bold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontFamily = bold,
        fontSize = 22.sp
    ),
    h3 = TextStyle(
        fontFamily = bold,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontFamily = regular,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = regular,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = regular,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)