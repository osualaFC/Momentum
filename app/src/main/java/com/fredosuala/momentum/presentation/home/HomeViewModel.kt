package com.fredosuala.momentum.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredosuala.momentum.domain.usecases.UseCases
import com.fredosuala.momentum.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(TaskState())
    val state : State<TaskState> = _state


     fun getTodayTasks(){
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