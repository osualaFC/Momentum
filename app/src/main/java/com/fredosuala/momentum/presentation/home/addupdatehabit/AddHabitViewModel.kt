package com.fredosuala.momentum.presentation.home.addupdatehabit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.usecases.UseCases
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(AddUpdateHabitState())
    val state : State<AddUpdateHabitState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun addFreq(str : String) {
        state.value.freq.add(str)
        val freq = state.value.freq
        _state.value = _state.value.copy(freq = freq)
    }
    fun removeFreq(str : String) {
        state.value.freq.remove(str)
        val freq = state.value.freq
        _state.value = _state.value.copy(freq = freq)
    }

    fun onEvent(event : AddHabitEvents) {
        when(event) {
            is AddHabitEvents.EnteredHabitName -> {
                _state.value = state.value.copy(habitName = event.value)
            }
            is AddHabitEvents.SelectReminder -> {
                _state.value = state.value.copy(reminder = event.value)
            }
            is AddHabitEvents.SelectNotification -> {
                _state.value = state.value.copy(notification = event.value)
            }
        }
    }

    fun addUpdateHabit() {
        val habit = if(_state.value.habit == null)
            Habit(id = 0, name =_state.value.habitName, frequency = _state.value.freq,
            reminder = _state.value.reminder, icon = 0, cancelled = !_state.value.notification)
        else updateHabit()
        viewModelScope.launch {
            try {
                if (!isNameValid(_state.value.habitName)) {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            message =  "Habit name should have atleast 3 characters"
                        )
                    )
                } else if (!isFreqValid(_state.value.freq)) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Select atleast one day to perform this habit"
                            )
                        )
                }
                else {
                    Log.i("TAG", "addHabit: ${Gson().toJson(habit)}")
                    if(_state.value.habit == null)
                        habit?.let { useCases.createHabit(it) }
                    else
                        habit?.let { useCases.updateHabit(it) }
                    _eventFlow.emit(UiEvent.HabitAdded)
                }
            } catch (e : Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = " Something went wrong. Couldn't save info"
                    )
                )
            }
        }
    }

    private fun updateHabit(): Habit? {
        val updatedHabit  = _state.value.habit
        updatedHabit?.frequency = _state.value.freq
        updatedHabit?.name = _state.value.habitName
        updatedHabit?.reminder = _state.value.reminder
        return updatedHabit
    }

    private fun isNameValid(str: String): Boolean {
        return str.isNotEmpty() && str.length >= 3
    }

    private fun isFreqValid(list : List<String>): Boolean{
        return !list.isNullOrEmpty()
    }
}

sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
    object HabitAdded: UiEvent()
}