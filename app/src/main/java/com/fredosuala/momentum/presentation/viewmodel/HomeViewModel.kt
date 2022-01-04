package com.fredosuala.momentum.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.fredosuala.momentum.R
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.usecases.UseCases
import com.fredosuala.momentum.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var _task = MutableStateFlow<Resource<List<TaskDomain>>>(Resource.Loading())
    val task : StateFlow<Resource<List<TaskDomain>>>
    get() = _task

    private var _addHabitState = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val addHabitState : StateFlow<Resource<Boolean>>
        get() = _addHabitState

    private val taskExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _task.value = Resource.Error(throwable.message ?:  "Something went wrong", null)
    }

    private val addHabitExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _addHabitState.value = Resource.Error(throwable.message ?:  "Something went wrong", null)
    }

    fun generateTodayTasks() {
        _task.value = Resource.Loading()
        viewModelScope.launch(taskExceptionHandler) {
          //  Log.i("TAG", "generateTodayTasks: hiii")
           _task.value = Resource.Success(useCases.generateTodayTasks())

          //  Log.i("TAG", "generateTodayTasks: ${_task.value.data}")
        }
    }

    fun addHabit(habit : Habit) {
        _addHabitState.value = Resource.Loading()
        viewModelScope.launch(addHabitExceptionHandler) {
           // Log.i("TAG", "addHabit: helllo")
            _addHabitState.value = Resource.Success(useCases.createHabit(habit))
           // Log.i("TAG", "addHabit: ${_addHabitState.value.data}")
        }
    }

    fun nukeTable() {
        viewModelScope.launch {
            useCases.clearTable()
        }
    }


}