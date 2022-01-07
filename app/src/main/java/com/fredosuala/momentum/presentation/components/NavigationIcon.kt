package com.fredosuala.momentum.presentation.components

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
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.ui.theme.LightGreen
import com.fredosuala.momentum.presentation.ui.theme.SubGreen

@Composable
fun NavigationIcon(navController: NavController) {
    Card(
        backgroundColor = LightGreen,
        shape = AbsoluteRoundedCornerShape(5.dp),
        modifier = Modifier
            .clickable { navController.popBackStack() }
            .padding(16.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Image(
            modifier = Modifier.padding(5.dp),
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Back",
            colorFilter = ColorFilter.tint(SubGreen),
            alignment = Alignment.Center
        )
    }
}