package com.fredosuala.momentum.domain.usecases

import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.repository.Repository
import com.fredosuala.momentum.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateTodayTasks @Inject constructor(
    private val repository: Repository
) {
     operator fun invoke() : Flow<Resource<List<TaskDomain>>> {
        return repository.getTodayTask()
    }
}