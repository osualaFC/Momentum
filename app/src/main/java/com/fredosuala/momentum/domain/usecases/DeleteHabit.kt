package com.fredosuala.momentum.domain.usecases

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.repository.Repository
import javax.inject.Inject

class DeleteHabit @Inject constructor(
    private val repository : Repository
){
    suspend operator fun invoke(habit: Habit) {
        repository.deleteHabit(habit)
    }
}