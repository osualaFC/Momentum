package com.fredosuala.momentum.presentation.home.addupdatehabit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.addupdatehabit.AddHabitViewModel
import com.fredosuala.momentum.presentation.ui.theme.*

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
                        color = SubTextDark
                    )
//                    AnimatedVisibility(
//                        visible = viewModel.state.value.freq.size == 7
//                    ) {
//                        Text(
//                            text = stringResource(id = R.string.everyday),
//                            style = MaterialTheme.typography.body2,
//                            color = SubTextDark
//                        )
//                    }
                }
            }
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
            )
            DayItem(viewModel = viewModel)
        }

    }
}

val days = listOf(R.string.mon, R.string.tues, R.string.wed, R.string.thurs,R.string.fri,R.string.sat,R.string.sun)
val list = listOf("Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")


@Composable
fun DayItem(viewModel: AddHabitViewModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
               horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(days) {index, item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val selected = rememberSaveable { mutableStateOf(false) }
                Text(
                    text = stringResource(id = item),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary
                )
                Card(
                    modifier = Modifier
                        .height(40.dp)
                        .width(30.dp)
                        .selectable(selected = selected.value, onClick = {
                            selected.value = !selected.value
                            if (selected.value) viewModel.addFreq(list[index])
                            else viewModel.removeFreq(list[index])
                        }) ,
                    backgroundColor = if (selected.value) BgDark else BgLight,
                    content = {}
                )
            }
        }
    }
}
