package com.fredosuala.momentum.presentation.home.addhabit

sealed class AddHabitEvents {
    data class EnteredHabitName(val value: String): AddHabitEvents()
    data class SelectReminder(val value: String): AddHabitEvents()
    data class SelectNotification(val value: Boolean): AddHabitEvents()
}
