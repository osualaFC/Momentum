package com.fredosuala.momentum.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.presentation.ui.theme.DarkAltText
import com.fredosuala.momentum.presentation.ui.theme.LightAltText
import com.fredosuala.momentum.presentation.ui.theme.MainGreen
import com.fredosuala.momentum.presentation.ui.theme.SubGreen

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled : Boolean = true,
    onClick: () -> Unit = { }
) {
    val enabledGradient =
        Brush.horizontalGradient(listOf(MainGreen, SubGreen))
    val disabledGradient =
        Brush.horizontalGradient(listOf(LightAltText, DarkAltText))
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        enabled = enabled
    ) {
        Box(
            modifier = Modifier
                .background(if (enabled) enabledGradient else disabledGradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}