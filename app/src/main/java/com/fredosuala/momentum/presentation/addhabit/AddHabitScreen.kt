package com.fredosuala.momentum.presentation.addhabit

import android.app.TimePickerDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.components.AppButton
import com.fredosuala.momentum.presentation.components.Header
import com.fredosuala.momentum.presentation.components.NavigationIcon
import com.fredosuala.momentum.presentation.ui.theme.*
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun AddHabitScreen(
    navController: NavController,
    bottomBarState: MutableState<Boolean>,
    viewModel : AddHabitViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val habitNameState = viewModel.habitName.value
    val reminderState  = viewModel.reminder.value
    val notificationState = viewModel.notification.value


    ScreenContent(
        bottomBarState,
        navController,
        scaffoldState,
        habitNameState,
        reminderState,
        notificationState,
        viewModel
    )
}

@Composable
private fun ScreenContent(
    bottomBarState: MutableState<Boolean>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    habitName: String,
    reminder : String,
    enableNotf: Boolean,
    viewModel: AddHabitViewModel
) {
    bottomBarState.value = false

    LaunchedEffect(key1 = true)  {
        viewModel.eventFlow.collectLatest {  event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.HabitAdded -> {
                    navController.popBackStack()
                }
            }

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = { NavigationIcon(navController) },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxHeight()
            ) {
                Header(R.string.new_habit)
                Spacer(modifier = Modifier.height(15.dp))
                Form(habitName, viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                HabitFrequency(viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                Reminder(reminder, viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                Notification(enableNotf, viewModel)
                Spacer(modifier = Modifier.height(100.dp))
                AppButton(
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                        .fillMaxWidth(),
                    text = "Start this habit",
                    onClick = { viewModel.addHabit()}
                )
            }
        }
    }
}


@Composable
fun Form(habit: String, viewModel: AddHabitViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = habit,
            onValueChange = { viewModel.onEvent(AddHabitEvents.EnteredHabitName(it)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_habit_name)) },
            shape = AbsoluteRoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                textColor = MaterialTheme.colors.onSurface
            )
        )
    }
}

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