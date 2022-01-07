package com.fredosuala.momentum.presentation.addhabit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.addhabit.components.Form
import com.fredosuala.momentum.presentation.addhabit.components.HabitFrequency
import com.fredosuala.momentum.presentation.addhabit.components.Notification
import com.fredosuala.momentum.presentation.addhabit.components.Reminder
import com.fredosuala.momentum.presentation.components.AppButton
import com.fredosuala.momentum.presentation.components.Header
import com.fredosuala.momentum.presentation.components.NavigationIcon
import kotlinx.coroutines.flow.collectLatest

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