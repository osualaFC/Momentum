package com.fredosuala.momentum.presentation.blog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fredosuala.momentum.R

@Composable
fun BlogScreen() {
    ScreenContent()
}

@Composable
private fun ScreenContent() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Blog")
    }
}