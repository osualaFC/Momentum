package com.fredosuala.momentum.domain.usecases

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.repository.Repository
import com.fredosuala.momentum.domain.util.CalenderUtil
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GenerateTodayTasks @Inject constructor(
    private val repository: Repository,
    @ApplicationContext val application: Application
) {

    suspend operator fun invoke() : List<TaskDomain> {

        val tasks = mutableListOf<TaskDomain>()

         repository.getAllHabitWithTask().collect { list ->
            // Log.i("TAG", "invoke: ${list}")
            for(habit in list) {
               // Log.i("TAG", "invoke: ${habit.habit.frequency[0]} ")
                if(habit.habit.frequency.contains(CalenderUtil.getCurrentWeekDayText(application))) {
                   val task = TaskDomain(habit.habit.id, habit.habit.name, Status.PENDING)
                    Log.i("TAG", "invoke: ${Gson().toJson(tasks)}")
                    if(!CalenderUtil.isDuplicateTask(task, tasks)) {
                        repository.addTask(task.toEntity())
                        tasks.add(task)
                    }
                }
            }
        }
        Log.i("TAG", "nawaooo: ${Gson().toJson(tasks)}")
        return tasks
    }
}