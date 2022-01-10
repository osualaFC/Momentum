package com.fredosuala.momentum.domain.model

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.data.entity.Task
import com.fredosuala.momentum.domain.util.CalenderUtil

data class TaskDomain(
    val habitId : Long,
    var status: Status,
    val habit : Habit,
    val date: String = CalenderUtil.getCurrentDateText()
) {
    fun toEntity() : Task {
        return Task(
            habitId = habitId,
             status = status,
            date = date
        )
    }
}