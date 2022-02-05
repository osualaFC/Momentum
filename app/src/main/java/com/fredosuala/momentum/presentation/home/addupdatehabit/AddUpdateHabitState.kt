package com.fredosuala.momentum.presentation.home.addupdatehabit

import com.fredosuala.momentum.data.entity.Habit

data class AddUpdateHabitState (
    var habitName : String = "",
    var reminder : String = "10:00AM",
    var notification : Boolean = true,
    var freq : ArrayList<String> =  arrayListOf("Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
    var habit : Habit? = null
)