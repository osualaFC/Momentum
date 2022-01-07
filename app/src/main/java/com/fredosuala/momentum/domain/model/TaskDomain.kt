package com.fredosuala.momentum.domain.model

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.data.entity.Task

data class TaskDomain(
    val habitId : Long,
    var status: Status,
    val habit : Habit,
    val currentTimestamp: Long = System.currentTimeMillis()
) {
    fun toEntity() : Task {
        return Task(
            habitId = habitId,
             status = status
        )
    }
}