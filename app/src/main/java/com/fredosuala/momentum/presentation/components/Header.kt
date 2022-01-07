package com.fredosuala.momentum.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Header(@StringRes str : Int) {
    Text(
        text = stringResource(id = str),
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSurface
    )
}