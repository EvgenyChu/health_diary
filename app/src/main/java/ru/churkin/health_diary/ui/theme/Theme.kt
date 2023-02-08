package ru.churkin.health_diary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkPrimaryDarkColor,
    primaryVariant = darkPrimaryLightColor,
    secondary = darkSecondaryDarkColor,
    secondaryVariant = darkSecondaryColor,
    background = darkPrimaryColor,
    surface = darkPrimaryColor,
    onPrimary = darkPrimaryTextColor,
    onSecondary = darkSecondaryTextColor,
    onBackground = darkPrimaryTextColor,
    onSurface = darkPrimaryTextColor,
    error = errorColor,
    onError = errorTextColor
)

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryLightColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryLightColor,
    background = backgroundColor,
    surface = backgroundColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor,
    onBackground = backgroundTextColor,
    onSurface = backgroundTextColor,
    error = errorColor,
    onError = errorTextColor
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}