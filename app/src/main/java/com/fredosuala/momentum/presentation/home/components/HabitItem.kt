package com.fredosuala.momentum.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.presentation.ui.theme.Flame

@Composable
fun HabitItem(task : TaskDomain) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp, 16.dp)
        ) {
            Image(
                painter = painterResource(id = task.habit.icon),
                contentDescription = null,
            )
            Text(
                text = task.habit.name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                AnimatedVisibility(visible = task.habit.score > 0 ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Flame)
                        )
                        Text(
                            text = task.habit.score.toString(),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSecondary)
                    }
                }

                Text(
                    text = task.habit.reminder,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface)
            }
        }
    }
}