package com.fredosuala.momentum.presentation.home.habitdetails

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.components.AppTopBar
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.components.AppButton
import com.fredosuala.momentum.presentation.home.components.HabitInfo
import com.fredosuala.momentum.presentation.home.habitdetails.components.DetailMainCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HabitDetailScreen(navController: NavController, habitId: Long?, bottomBarState: MutableState<Boolean>) {
    val scaffoldState = rememberScaffoldState()
    val viewModel : HabitDetailViewModel = hiltViewModel()
    val state = viewModel.state.value
    ScreenContent(scaffoldState, navController, state, viewModel, habitId, bottomBarState)
}

@Composable
private fun ScreenContent(
    scaffoldState: ScaffoldState,
    navController: NavController,
    state: HabitDetailState,
    viewModel: HabitDetailViewModel,
    habitId: Long?,
    bottomBarState: MutableState<Boolean>
) {
    
    bottomBarState.value = false


    LaunchedEffect(key1 = true)  {
        viewModel.habitId = habitId!!
        viewModel.getHabit()
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            state.habit?.name?.let {
                AppTopBar(
                    navController = navController,
                    title = it,
                    endIcon = R.drawable.ic_edit,
                    endContentDes = "edit habit"
                ) {}
            }
        },
        scaffoldState = scaffoldState

    ) {innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxHeight()) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    DetailMainCard(state = state)
                    HabitInfo(state = state)
                    Spacer(modifier = Modifier.height(50.dp))
                    AppButton(
                        modifier = Modifier
                            .padding(16.dp, 20.dp)
                            .fillMaxWidth(),
                        text = "Mark Habit as Completed"
                    ){
                        viewModel.onEvent(HabitDetailEvents.SetAsComplete)
                        navController.popBackStack()
                    }
                    AppButton(
                        modifier = Modifier
                            .padding(16.dp, 20.dp)
                            .fillMaxWidth(),
                        text = "Mark Habit as Missed",
                        normal = false
                    ){
                        viewModel.onEvent(HabitDetailEvents.SetAsMissed)
                    }
                }

            }
        }
    }
}
