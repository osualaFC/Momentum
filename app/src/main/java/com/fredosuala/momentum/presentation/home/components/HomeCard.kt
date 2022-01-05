package com.fredosuala.momentum.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.HomeViewModel

@Composable
fun HomeCard() {
    Card(
        modifier = Modifier
            .clickable {  }
            .padding(16.dp, 0.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(modifier = Modifier.padding(0.dp, 24.dp)) {
                Text(
                    text = stringResource(id = R.string.home_page),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = stringResource(id = R.string.anonymous),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_undraw_home),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.padding(0.dp, 24.dp)
            )
        }
    }
}