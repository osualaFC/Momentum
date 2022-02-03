package com.fredosuala.momentum.presentation.home.addhabit

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.home.addhabit.components.Form
import com.fredosuala.momentum.presentation.home.addhabit.components.HabitFrequency
import com.fredosuala.momentum.presentation.home.addhabit.components.Reminder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddUpdateHabit(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
    viewModel : AddHabitViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val habitName = viewModel.habitName.value
    val reminder  = viewModel.reminder.value

    LaunchedEffect(key1 = true)  {
        viewModel.eventFlow.collectLatest {  event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
                is UiEvent.HabitAdded -> {
                    coroutineScope.launch {
                        bottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                    }
                }
            }
        }
    }

    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 4.dp
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp, 16.dp)
                .fillMaxHeight()
        ) {
            item {
                Text(
                    text = "Add a new habit",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Form(habitName, viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                Reminder(reminder, viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                HabitFrequency(viewModel)
                Spacer(modifier = Modifier.height(5.dp))
               Button(
                   onClick = {viewModel.addHabit()},
                   elevation = null,
                   colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                   modifier = Modifier
                       .padding(0.dp, 10.dp, 0.dp, 0.dp)
                       .fillMaxWidth(),

               ) {
                   Text(
                       text = "Start this habit",
                       style = MaterialTheme.typography.h2,
                       color = MaterialTheme.colors.primary
                   )
               }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}