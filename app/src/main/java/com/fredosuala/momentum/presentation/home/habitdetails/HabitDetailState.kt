package com.fredosuala.momentum.presentation.home.habitdetails

import com.fredosuala.momentum.data.entity.Habit

data class HabitDetailState(
    var habit: Habit? = null,
    var isLoading: Boolean = true
)