package com.fredosuala.momentum.domain.usecases

import android.util.Log
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.repository.Repository
import com.google.gson.Gson
import javax.inject.Inject

class UpdateHabit @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(habit: Habit) {
        return repository.updateHabit(habit)
    }
}