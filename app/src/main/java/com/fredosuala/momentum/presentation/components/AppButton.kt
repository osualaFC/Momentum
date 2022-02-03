package com.fredosuala.momentum.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.presentation.ui.theme.*

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    normal : Boolean = true,
    onClick: () -> Unit = { }
) {
    val normalGradient =
        Brush.horizontalGradient(listOf(MainGreen, SubGreen))
    val lightGradient =
        Brush.horizontalGradient(listOf(LightGreen, MainGreen))
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, color = MaterialTheme.colors.background)
        }
    }
}