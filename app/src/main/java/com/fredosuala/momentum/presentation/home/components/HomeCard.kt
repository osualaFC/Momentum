package com.fredosuala.momentum.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R

@Composable
fun HomeCard() {

    Row(modifier = Modifier
        .padding()
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                text = "Hello User\nGood Morning,",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "You have some important\ntasks to do for today",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_happy),
                contentDescription = "happy",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}