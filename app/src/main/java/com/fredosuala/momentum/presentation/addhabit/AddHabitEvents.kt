package com.fredosuala.momentum.presentation.addhabit

sealed class AddHabitEvents {
    data class EnteredHabitName(val value: String): AddHabitEvents()
    data class SelectReminder(val value: String): AddHabitEvents()
    data class SelectNotification(val value: Boolean): AddHabitEvents()
}
