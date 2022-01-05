package com.fredosuala.momentum.presentation.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.domain.model.TaskDomain
import com.fredosuala.momentum.domain.usecases.UseCases
import com.fredosuala.momentum.domain.util.CalenderUtil
import com.fredosuala.momentum.domain.util.Resource
import com.fredosuala.momentum.presentation.home.components.HomeEvent
import com.fredosuala.momentum.presentation.util.ResourceUtil
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf<TaskState>(TaskState())
    val state : State<TaskState> = _state

    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val todayTasksExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = state.value.copy(isLoading = false, isEmpty = true)
    }

    init{
        getAllHabits()
    }

    private fun getAllHabits(){
        viewModelScope.launch {
            useCases.generateTodayTasks().collectLatest {
                when(it) {
                    is Resource.Success -> {
                        val tasks = it.data
                        if(!tasks.isNullOrEmpty()) {
                            _state.value = state.value.copy(isLoading = false, tasks = tasks)
                        } else {
                            _state.value = state.value.copy(isLoading = false, isEmpty = true)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoading = false, isEmpty = true)
                    }
                }
            }
        }
    }




}