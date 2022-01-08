package com.fredosuala.momentum.presentation.home.addhabit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.addhabit.AddHabitEvents
import com.fredosuala.momentum.presentation.home.addhabit.AddHabitViewModel

@Composable
fun Notification(enableNotf: Boolean?, viewModel: AddHabitViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = AbsoluteRoundedCornerShape(5.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.notification),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = enableNotf ?: true,
                    onCheckedChange = { viewModel.onEvent(AddHabitEvents.SelectNotification(it))}
                )
            }
        }
    }
}