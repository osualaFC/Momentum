package com.fredosuala.momentum.presentation.home.dashboard

import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.util.CalenderUtil

data class TaskState(
    var tasks : List<TaskDomain> = emptyList(),
    var isEmpty : Boolean = false,
    var isLoading : Boolean = true,
    val today: String = CalenderUtil.getCurrentDateText()
)
