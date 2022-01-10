package com.fredosuala.momentum.presentation.home.habitdetails

import android.icu.lang.UCharacter.DecompositionType.NARROW
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.components.AppTopBar
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.components.AppButton
import com.fredosuala.momentum.presentation.home.components.HabitInfo
import com.fredosuala.momentum.presentation.home.habitdetails.components.DetailMainCard
import com.fredosuala.momentum.presentation.ui.theme.*

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
            Column {
                DetailMainCard(state = state)
                HabitInfo(state = state)
                Spacer(modifier = Modifier.height(50.dp))
                AppButton(
                    modifier = Modifier
                    .padding(16.dp, 20.dp)
                    .fillMaxWidth(),
                    text = "Mark Habit as Completed"
                ){}
                AppButton(
                    modifier = Modifier
                    .padding(16.dp, 20.dp)
                    .fillMaxWidth(),
                    text = "Mark Habit as Missed",
                    normal = false
                ){}
            }
        }
    }
}
