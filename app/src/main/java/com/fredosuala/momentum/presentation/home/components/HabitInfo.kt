package com.fredosuala.momentum.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.habitdetails.HabitDetailState
import com.fredosuala.momentum.presentation.ui.theme.*


@Composable
fun HabitInfo(state: HabitDetailState, modifier: Modifier) {
    val streak = state.habit?.score ?: 0
    val completed = state.habit?.completed ?: 0
    val rate = if(completed != 0 && streak != 0) (completed / streak) * 100 else 0
    val missed = state.habit?.missed ?: 0
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailSubCard(
                streak = streak,
                label = "Longest Streak",
                cardColor = Flame,
                iconColor = LIGHTRED,
                icon = R.drawable.ic_flame
            )

            DetailSubCard(
                streak = completed,
                label = "Completed",
                cardColor = BLUE,
                iconColor = LIGHTBLUE,
                icon = R.drawable.ic_complete
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailSubCard(
                streak = rate,
                label = "Completion Rate",
                cardColor = INDIGO,
                iconColor = LIGHTINDIGO,
                icon = R.drawable.ic_graph
            )

            DetailSubCard(
                streak = missed,
                label = "Missed",
                cardColor = VIOLET,
                iconColor = LIGHTVIOLET,
                icon = R.drawable.ic_missed
            )
        }
    }
}

@Composable
fun DetailSubCard(
    streak : Int,
    label : String,
    cardColor: Color,
    iconColor: Color,
    @DrawableRes icon : Int
) {

    val streakText = if(label == "Completion Rate") "$streak%"
                    else if(streak > 1) "$streak days" else "$streak day"

    Card(
        modifier = Modifier
            .padding(10.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(0.dp, 0.dp,20.dp, 0.dp)
            ) {
                Text(
                    text = streakText,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp
                )
            }
            Card(
                shape = RoundedCornerShape(50.dp),
                backgroundColor = cardColor,
                contentColor = iconColor
            ) {
                Image(
                    modifier = Modifier.padding(5.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(iconColor),
                    alignment = Alignment.Center
                )
            }
        }
    }
}