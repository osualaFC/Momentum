package com.fredosuala.momentum.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.presentation.ui.theme.MainGreen
import com.fredosuala.momentum.presentation.util.Constants

@Composable
fun HabitItem(task: TaskDomain, navController: NavController) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
        modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
            .clickable {
                navController.navigate("${Constants.HABITDETAILSCREEN}/${task.habitId}")
            }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.habit.reminder,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.surface
                )
                Spacer(modifier = Modifier.width(10.dp))
                Divider(color = MainGreen, thickness = 2.dp)
            }
            Text(
                text = task.habit.name,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "in 6hrs",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.surface
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_alarm_undone),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.surface)
                )
            }
        }
    }
}