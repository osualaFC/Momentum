package com.fredosuala.momentum.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.TaskState

@Composable
fun HomeDetails(state : TaskState) {
    Box(modifier = Modifier
        .padding(16.dp, 16.dp, 16.dp, 0.dp)
        .fillMaxWidth()
    ) {
        Column() {
            if (state.isEmpty) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.empty),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        textAlign = TextAlign.Left,
                        text = stringResource(id = R.string.action_header),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        textAlign = TextAlign.Right,
                        text = state.today,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                LazyColumn {
                    items(state.tasks) { task ->
                        HabitItem(task)
                        Spacer(modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth())
                    }
                }

            }
        }
    }
}