package com.fredosuala.momentum.presentation.home.addhabit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredosuala.momentum.R
import com.fredosuala.momentum.presentation.home.addhabit.AddHabitEvents
import com.fredosuala.momentum.presentation.home.addhabit.AddHabitViewModel

@Composable
fun Form(habit: String, viewModel: AddHabitViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = habit,
            onValueChange = { viewModel.onEvent(AddHabitEvents.EnteredHabitName(it)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_habit_name)) },
            shape = AbsoluteRoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                textColor = MaterialTheme.colors.onSurface
            )
        )
    }
}