package com.fredosuala.momentum.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = MainDark,
    primaryVariant = ShadeBlack,
    secondary = MainGreen,
    background = MainDark,
    surface = SubGreen,
    onSurface = DarkMainText,
    onSecondary = DarkAltText
)

private val LightColorPalette = lightColors(
    primary = MainLight,
    primaryVariant = MainLightV,
    secondary = MainGreen,
    background = MainLight,
    surface = SubGreen,
    onSurface = LightMainText,
    onSecondary = LightAltText

//    // Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,

)

@Composable
fun MomentumAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
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