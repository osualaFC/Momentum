package com.fredosuala.momentum.presentation.addhabit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.addhabit.AddHabitViewModel
import com.fredosuala.momentum.presentation.ui.theme.LightAltText
import com.fredosuala.momentum.presentation.ui.theme.MainGreen

/**code smell --fix this**/
@Composable
fun HabitFrequency(viewModel: AddHabitViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = AbsoluteRoundedCornerShape(5.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 0.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.primaryVariant,
                elevation = 0.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp, 0.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.frequency),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface
                    )
                    AnimatedVisibility(
                        visible = viewModel.freq.value?.size == 7
                    ) {
                        Text(
                            text = stringResource(id = R.string.everyday),
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.mon),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)
                                viewModel.addFreq("Monday")
                            else viewModel.removeFreq("Monday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.tues),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)
                                viewModel.addFreq("Tuesday")
                            else viewModel.removeFreq("Tuesday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.wed),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)  viewModel.addFreq("Wednesday")
                            else viewModel.removeFreq("Wednesday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.thurs),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)  viewModel.addFreq("Thursday")
                            else viewModel.removeFreq("Thursday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.fri),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)  viewModel.addFreq("Friday")
                            else viewModel.removeFreq("Friday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.sat),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)  viewModel.addFreq("Saturday")
                            else viewModel.removeFreq("Saturday")
                        },
                        content = {},
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val selected = remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.sun),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected.value) MainGreen else LightAltText
                        ),
                        onClick = {
                            selected.value = !selected.value
                            if(selected.value)  viewModel.addFreq("Sunday")
                            else viewModel.removeFreq("Sunday")
                        },
                        content = {},
                    )
                }

            }
        }

    }
}
