package com.fredosuala.momentum.presentation.home.habitdetails

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fredosuala.momentum.presentation.components.AppTopBar
import com.fredosuala.momentum.R
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
    val freq = state.habit?.frequency?.size
    val repeat = if(freq == 7) "Everyday" else if(freq == 1) "Once a week" else "$freq times a week"
    val dark = isSystemInDarkTheme()
    val tint = if(!dark) LightGreen else MainGreen
    val textColor = if(!dark) LightMainText else DarkAltText


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
        Box(modifier = Modifier.padding(innerPadding)) {
            Card(
                modifier = Modifier
                    .padding(16.dp, 16.dp)
                    .fillMaxWidth(),
                elevation = 0.dp,
                shape = RoundedCornerShape(20.dp),
                backgroundColor = if(dark) LightGreen else MainGreen
            ) {
                Row(
                    modifier = Modifier
                        .padding(20.dp, 20.dp)
                ) {
                        state.habit?.icon?.let { painterResource(it) }?.let { Image(
                            painter = it,
                            contentDescription = "habit icon",
                            contentScale = ContentScale.FillBounds,
                            alignment = Alignment.CenterStart,
                            modifier = Modifier.padding(0.dp, 20.dp,0.dp,0.dp)
                        ) }

                    ConstraintLayout(

                    ) {
                        val (name, fq, rem) = createRefs()
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.constrainAs(name) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                        ) {
                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_anchor),
                                contentDescription = "Habit",
                                colorFilter = ColorFilter.tint(tint),
                                alignment = Alignment.Center
                            )
                            state.habit?.name?.let { Text(
                                text = it,
                                style = MaterialTheme.typography.body2,
                                color = textColor,
                            ) }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.constrainAs(fq) {
                                start.linkTo(parent.start)
                                top.linkTo(name.bottom)
                                bottom.linkTo(rem.top)
                            }
                        ) {
                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = "Frequency",
                                colorFilter = ColorFilter.tint(tint),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = repeat,
                                style = MaterialTheme.typography.body2,
                                color = textColor
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.constrainAs(rem) {
                                start.linkTo(parent.start)
                                top.linkTo(fq.bottom)
                                bottom.linkTo(parent.bottom)
                            }
                        ) {
                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_sync),
                                contentDescription = "Reminder",
                                colorFilter = ColorFilter.tint(tint),
                                alignment = Alignment.Center
                            )
                            state.habit?.reminder?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.body2,
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}