package com.fredosuala.momentum.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.components.HomeCard
import com.fredosuala.momentum.presentation.home.components.HomeDetails

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
   ScreenContent(state,  scaffoldState)
}

@Composable
private fun ScreenContent(taskState: TaskState, scaffoldState: ScaffoldState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )
        },
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