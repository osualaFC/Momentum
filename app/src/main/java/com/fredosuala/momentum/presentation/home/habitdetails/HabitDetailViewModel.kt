package com.fredosuala.momentum.presentation.home.habitdetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fredosuala.momentum.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.fredosuala.momentum.data.entity.Status
import com.fredosuala.momentum.domain.util.CalenderUtil
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    
    companion object{
        const val TAG = "HabitDetailViewModel"
    }
    var habitId : Long = -1
    private val today = CalenderUtil.getCurrentDateText()

    private val _state = mutableStateOf(HabitDetailState())
    val state : State<HabitDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event : HabitDetailEvents) {
        when(event) {
            is HabitDetailEvents.SetAsComplete -> completeTask()
            is HabitDetailEvents.SetAsMissed -> missedTask()
        }
    }

    fun getHabit() {
        viewModelScope.launch {
            try {
              val habit =  useCases.getHabit(habitId)
                habit.collectLatest {
                    _state.value = state.value.copy(habit = it, isLoading = false)
                }
            } catch (e : Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = "Something went wrong, why fetching info for this habit"
                    )
                )
            }
        }
    }

    private fun completeTask() {
        viewModelScope.launch {
            try {
                val habit = _state.value.habit
                val updatedHabit = habit?.copy(tracker = today, completed = habit.completed+1, score = habit.score+1)
                if (updatedHabit != null) {
                    useCases.updateHabit(updatedHabit)
                }
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = "Something went wrong, why updating this habit"
                    )
                )
            }
        }
    }

    private fun missedTask() {
        viewModelScope.launch {
            try {
                val habit = _state.value.habit
                val updatedHabit = habit?.copy(tracker = today, completed = habit.missed+1, score = habit.score+1)
                if (updatedHabit != null) {
                    useCases.updateHabit(updatedHabit)
                }
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = "Something went wrong, why updating this habit"
                    )
                )
            }
        }
    }
}


sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
}