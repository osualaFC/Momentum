package com.fredosuala.momentum.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.presentation.ui.theme.LightGreen
import com.fredosuala.momentum.presentation.ui.theme.SubGreen

@Composable
fun RightIcon(@DrawableRes icon : Int, contentDes : String,  modifier: Modifier = Modifier, onClick : () -> Unit) {
    Card(
        backgroundColor = LightGreen,
        shape = AbsoluteRoundedCornerShape(5.dp),
        modifier = modifier
            .clickable { onClick }
            .padding(16.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Image(
            modifier = Modifier.padding(5.dp),
            painter = painterResource(id = icon),
            contentDescription = contentDes,
            colorFilter = ColorFilter.tint(SubGreen),
            alignment = Alignment.Center
        )
    }
}