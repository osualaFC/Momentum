package com.fredosuala.momentum.domain.usecases

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabit @Inject constructor(private val repository: Repository) {

    operator fun invoke(id: Long) : Flow<Habit> {
        return repository.getHabit(id)
    }
}