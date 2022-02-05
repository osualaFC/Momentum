package com.fredosuala.momentum.presentation.home.habitdetails

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.components.AppButton
import com.fredosuala.momentum.presentation.home.addupdatehabit.AddUpdateHabitScreen
import com.fredosuala.momentum.presentation.home.components.HabitInfo
import com.fredosuala.momentum.presentation.ui.theme.Flame
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HabitDetailScreen(
    navController: NavController,
    habitId: Long?,
    bottomBarState: MutableState<Boolean>
) {
    val scaffoldState = rememberScaffoldState()
    val viewModel: HabitDetailViewModel = hiltViewModel()
    val state = viewModel.state.value
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()
    ScreenContent(
        scaffoldState,
        navController,
        state,
        viewModel,
        habitId,
        bottomBarState,
        showDialogState
    )
}

@Composable
private fun ScreenContent(
    scaffoldState: ScaffoldState,
    navController: NavController,
    state: HabitDetailState,
    viewModel: HabitDetailViewModel,
    habitId: Long?,
    bottomBarState: MutableState<Boolean>,
    showDialogState: Boolean
) {

    bottomBarState.value = false
    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var showMenu by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.habitId = habitId!!
        viewModel.getHabit()
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            Box(Modifier.defaultMinSize(minHeight = 1.dp)) {
                AddUpdateHabitScreen(bottomState, viewModel.habit)
            }
        },
        sheetBackgroundColor = MaterialTheme.colors.secondary,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
        scrimColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .padding()
                .fillMaxHeight()
        ) {
            Column() {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val  (leftIcon, header, rightIcon) = createRefs()
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.constrainAs(leftIcon){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = MaterialTheme.colors.secondary)
                    }
                    Text(
                        text = "",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.constrainAs(header){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(leftIcon.end)
                            end.linkTo(rightIcon.start)
                        }
                    )
                    Box(
                        modifier = Modifier.constrainAs(rightIcon){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }){
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(Icons.Default.MoreVert, "", tint = MaterialTheme.colors.secondary)
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                showMenu = false
                                coroutineScope.launch {
                                    bottomState.animateTo(ModalBottomSheetValue.HalfExpanded)
                                }
                            }) {
                                Text("Update")
                            }
                            DropdownMenuItem(onClick = {
                                showMenu = false
                                viewModel.onOpenDialogClicked()
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(90.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        ConstraintLayout {
                            val (info, btn1, btn2) = createRefs()
                            HabitInfo(state = state,
                                modifier = Modifier.constrainAs(info){
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                            AppButton(
                                modifier = Modifier
                                    .constrainAs(btn1) {
                                        bottom.linkTo(btn2.top)
                                        top.linkTo(info.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .padding(16.dp, 20.dp)
                                    .fillMaxWidth(),
                                text = "Mark Habit as Completed"
                            ) {
                                viewModel.onEvent(HabitDetailEvents.SetAsComplete)
                                navController.popBackStack()
                            }
                            AppButton(
                                modifier = Modifier
                                    .constrainAs(btn2) {
                                        bottom.linkTo(parent.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .padding(16.dp, 20.dp)
                                    .fillMaxWidth(),
                                text = "Mark Habit as Missed",
                                normal = false
                            ) {
                                viewModel.onEvent(HabitDetailEvents.SetAsMissed)
                            }
                        }
                    }

                }
            }
            if (showDialogState) {
                AlertDialog(
                    onDismissRequest = { viewModel.onDialogDismiss() },
                    title = { Text(text = "Delete this Habit") },
                    text = {
                        Text(
                            text = "Are you sure you want to delete this? This action is irreversible"
                        )
                    },
                    backgroundColor = MaterialTheme.colors.secondary,
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.onDialogConfirm()
                                navController.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = MaterialTheme.colors.background
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                disabledElevation = 0.dp
                            )
                        ) {
                            Text(text = "Yes")
                        }
                    },

                    dismissButton = {
                        Button(
                            onClick = {
                                viewModel.onDialogDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = Flame
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                disabledElevation = 0.dp
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    }
}
