package com.fredosuala.momentum.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BgDark,
    primaryVariant = Border,
    secondary = BgLight,
    background = BgDark,
    surface = SubTextDark,
    onSurface = MainTextDark,
    onSecondary = SubTextDark
)

private val LightColorPalette = lightColors(
    primary = BgLight,
    primaryVariant = Border,
    secondary = BgDark,
    background = BgLight,
    surface = SubTextLight,
    onSurface = MainTextLight,
    onSecondary = SubTextLight

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