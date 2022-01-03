package com.fredosuala.momentum.data.repository

import com.fredosuala.momentum.data.dao.Moment2mDao
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.HabitWithTasks
import com.fredosuala.momentum.data.entity.Task
import com.fredosuala.momentum.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val dao : Moment2mDao) : Repository {

    override suspend fun addHabit(habit: Habit) {
      return dao.addHabit(habit)
    }

    override suspend fun addTask(task: Task) {
       return dao.addTask(task)
    }

    override suspend fun updateHabit(habit: Habit) {
       return dao.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        return dao.deleteHabit(habit)
    }

    override suspend fun nukeTable() {
       return dao.nukeTable()
    }

    override fun getAllHabitWithTask(): Flow<List<HabitWithTasks>> {
       return dao.getAllHabitsWithTasks()
    }

    override fun getHabit(id: Long): Flow<Habit> {
       return dao.getHabitById(id)
    }

}