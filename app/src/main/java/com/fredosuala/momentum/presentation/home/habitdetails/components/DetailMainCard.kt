package com.fredosuala.momentum.presentation.home.habitdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.habitdetails.HabitDetailState
import com.fredosuala.momentum.presentation.ui.theme.DarkAltText
import com.fredosuala.momentum.presentation.ui.theme.LightGreen
import com.fredosuala.momentum.presentation.ui.theme.LightMainText
import com.fredosuala.momentum.presentation.ui.theme.MainGreen

@Composable
fun DetailMainCard(state: HabitDetailState) {

    val freq = state.habit?.frequency?.size
    val repeat = if(freq == 7) "Everyday" else if(freq == 1) "Once a week" else "$freq times a week"
    val dark = isSystemInDarkTheme()
    val tint = if(!dark) LightGreen else MainGreen

    Card(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.habit?.icon?.let { painterResource(it) }?.let { Image(
                painter = it,
                contentDescription = "habit icon",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.CenterStart,
                modifier = Modifier.padding(0.dp, 20.dp,0.dp,0.dp)
            ) }

            ConstraintLayout(

            ) {
                val (name, fq, rem) = createRefs()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(name) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                ) {
                    Image(
                        modifier = Modifier.padding(5.dp),
                        painter = painterResource(id = R.drawable.ic_anchor),
                        contentDescription = "Habit",
                        colorFilter = ColorFilter.tint(tint),
                        alignment = Alignment.Center
                    )
                    state.habit?.name?.let { Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface,
                    ) }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(fq) {
                        start.linkTo(parent.start)
                        top.linkTo(name.bottom)
                        bottom.linkTo(rem.top)
                    }
                ) {
                    Image(
                        modifier = Modifier.padding(5.dp),
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Frequency",
                        colorFilter = ColorFilter.tint(tint),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = repeat,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(rem) {
                        start.linkTo(parent.start)
                        top.linkTo(fq.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Image(
                        modifier = Modifier.padding(5.dp),
                        painter = painterResource(id = R.drawable.ic_sync),
                        contentDescription = "Reminder",
                        colorFilter = ColorFilter.tint(tint),
                        alignment = Alignment.Center
                    )
                    state.habit?.reminder?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}