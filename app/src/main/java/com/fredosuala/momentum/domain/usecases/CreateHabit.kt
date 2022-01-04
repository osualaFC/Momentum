package com.fredosuala.momentum.domain.usecases

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.repository.Repository
import javax.inject.Inject

class CreateHabit @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(habit: Habit) : Boolean {
         repository.addHabit(habit)
         return true
    }
}