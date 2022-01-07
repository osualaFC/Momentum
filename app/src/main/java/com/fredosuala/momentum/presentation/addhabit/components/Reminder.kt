package com.fredosuala.momentum.presentation.addhabit.components

import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.addhabit.AddHabitEvents
import com.fredosuala.momentum.presentation.addhabit.AddHabitViewModel
import com.fredosuala.momentum.presentation.ui.theme.SubGreen
import java.util.*


@Composable
fun Reminder(reminder : String?, viewModel: AddHabitViewModel) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = AbsoluteRoundedCornerShape(5.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 0.dp
    ) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]

        val timePickerDialog = TimePickerDialog(
            context,
            { _, hour: Int, minute: Int ->
                val min = if (minute < 10) "0${minute}" else minute
                val format = if (hour > 11) "PM" else "AM"
                viewModel.onEvent(AddHabitEvents.SelectReminder("$hour:$min $format"))
            }, hour, minute, true
        )
        Row(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.reminder),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier.clickable {
                    timePickerDialog.show()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reminder ?: "",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Image(
                    modifier = Modifier.padding(5.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "Open Time Picker",
                    colorFilter = ColorFilter.tint(SubGreen),
                    alignment = Alignment.Center
                )
            }
        }
    }
}