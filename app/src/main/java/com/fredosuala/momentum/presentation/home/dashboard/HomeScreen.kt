package com.fredosuala.momentum.presentation.home.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.home.addupdatehabit.AddUpdateHabitScreen
import com.fredosuala.momentum.presentation.home.components.HomeCard
import com.fredosuala.momentum.presentation.home.components.HomeDetails
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    bottomBarState : MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
   ScreenContent(state,  scaffoldState, navController,viewModel, bottomBarState)
}

@Composable
private fun ScreenContent(
    taskState: TaskState,
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: HomeViewModel,
    bottomBarState : MutableState<Boolean>) {

    LaunchedEffect(key1 = true)  {
       viewModel.getTodayTasks()
    }
    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    bottomBarState.value = bottomState.currentValue == ModalBottomSheetValue.Hidden


    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            Box(Modifier.defaultMinSize(minHeight = 1.dp)) {
                AddUpdateHabitScreen(bottomState)
            }
        },
        sheetBackgroundColor = MaterialTheme.colors.secondary,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
        scrimColor = Color.Transparent
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colors.background
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            bottomState.animateTo(ModalBottomSheetValue.HalfExpanded)
                        }
                    },
                    shape = RoundedCornerShape(20),
                    backgroundColor = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        tint = MaterialTheme.colors.background,
                        contentDescription = "Add"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            scaffoldState = scaffoldState
        ) {
            Column(
                modifier = Modifier.padding(30.dp, 0.dp, 10.dp, 0.dp)
            ) {
                HomeCard()
                Spacer(modifier = Modifier.height(15.dp))
                HomeDetails(taskState, navController)
            }
        }
    }
}