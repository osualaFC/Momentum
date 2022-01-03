package com.fredosuala.momentum.data.entity

import androidx.room.Embedded
import androidx.room.Relation


data class HabitWithTasks(
    @Embedded
    val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val tasks : List<Task>
)
