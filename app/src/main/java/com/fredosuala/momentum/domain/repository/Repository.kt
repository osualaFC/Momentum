package com.fredosuala.momentum.domain.repository

import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.HabitWithTasks
import com.fredosuala.momentum.data.entity.Task
import kotlinx.coroutines.flow.Flow

interface Repository {

     suspend fun addHabit(habit : Habit)

     suspend fun addTask(task : Task)

     suspend fun updateHabit(habit: Habit)

     suspend fun deleteHabit(habit: Habit)

     suspend fun nukeTable()

     fun getAllHabitWithTask() : Flow<List<HabitWithTasks>>

     fun getHabit(id: Long) : Flow<Habit>

}