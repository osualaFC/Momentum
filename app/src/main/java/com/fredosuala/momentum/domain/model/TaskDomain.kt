package com.fredosuala.momentum.domain.model

import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.data.entity.Task

data class TaskDomain(
    val habitId : Long,
    var name: String,
    var status: Status,
    val currentTimestamp: Long = System.currentTimeMillis()
) {
    fun toEntity() : Task {
        return Task(
            habitId = habitId,
             status = status
        )
    }
}