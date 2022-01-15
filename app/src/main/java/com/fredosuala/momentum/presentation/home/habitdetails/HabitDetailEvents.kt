package com.fredosuala.momentum.presentation.home.habitdetails

sealed class HabitDetailEvents {
    object SetAsComplete: HabitDetailEvents()
    object SetAsMissed: HabitDetailEvents()
}