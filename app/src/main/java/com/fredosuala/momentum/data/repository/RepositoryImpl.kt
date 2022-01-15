package com.fredosuala.momentum.data.repository

import android.util.Log
import com.fredosuala.momentum.data.dao.Moment2mDao
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.HabitWithTasks
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.data.entity.Task
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.repository.Repository
import com.fredosuala.momentum.domain.util.CalenderUtil
import com.fredosuala.momentum.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getAllHabits(): List<Habit>{
        return dao.getAllHabit()
    }

    override fun getTodayTask(): Flow<Resource<List<TaskDomain>>> = flow {
        emit(Resource.Loading())
        val habits = dao.getAllHabit()
        val tasks = mutableSetOf<TaskDomain>()
        for (habit in habits) {
            if(habit.frequency.contains(CalenderUtil.getCurrentWeekDayText()) &&  (habit.tracker == null ||
                habit.tracker != CalenderUtil.getCurrentDateText())) {
                val task = TaskDomain(habit.id, Status.PENDING, habit)
                dao.addTask(task.toEntity())
                tasks.add(task)
            }
        }
        val todayTasks = tasks.sortedBy { it.habit.reminder }.toList()
        emit(Resource.Success(todayTasks))
    }
}