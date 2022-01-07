package com.fredosuala.momentum.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.components.HomeCard
import com.fredosuala.momentum.presentation.home.components.HomeDetails
import com.fredosuala.momentum.presentation.ui.theme.LightGreen
import com.fredosuala.momentum.presentation.ui.theme.SubGreen
import com.fredosuala.momentum.presentation.util.Constants

@Composable
fun HomeScreen(
    navController: NavController,
    bottomBarState : MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
   ScreenContent(state,  scaffoldState, navController, bottomBarState)
}

@Composable
private fun ScreenContent(
    taskState: TaskState,
    scaffoldState: ScaffoldState,
    navController: NavController,
    bottomBarState : MutableState<Boolean>) {

    bottomBarState.value = true
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
                    navController.navigate(Constants.ADDHABITSCREEN)
                },
                shape = RoundedCornerShape(90),
                backgroundColor = LightGreen,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
            ) {
                Icon(Icons.Filled.Add, tint = SubGreen, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HomeCard()
            HomeDetails(taskState)
        }

    }
}