package com.fredosuala.momentum.presentation.home.addhabit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredosuala.momentum.R
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val icons = listOf(R.drawable.ic_habit_1,R.drawable.ic_habit_2,
        R.drawable.ic_habit_3,R.drawable.ic_habit_4,R.drawable.ic_habit_5)
    private val icon = icons.shuffled().first()

    private val _habitName = mutableStateOf("")
    val habitName : State<String> = _habitName

    private val _reminder = mutableStateOf("10:00AM")
    val reminder : State<String> = _reminder

    private val _notification = mutableStateOf(true)
    val notification : State<Boolean> = _notification

    var freq = mutableStateOf(
        mutableListOf("Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"))

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun addFreq(str : String) {
        freq.value.add(str)
        freq.value = freq.value
    }
    fun removeFreq(str : String) {
        freq.value.remove(str)
        freq.value = freq.value
    }

    fun onEvent(event : AddHabitEvents) {
        when(event) {
            is AddHabitEvents.EnteredHabitName -> {
                _habitName.value = event.value
            }
            is AddHabitEvents.SelectReminder -> {
                _reminder.value = event.value
            }
            is AddHabitEvents.SelectNotification -> {
                _notification.value = event.value
            }
        }
    }

    fun addHabit() {
        val habit = Habit(id = 0, name =_habitName.value, frequency = freq.value,
            reminder = _reminder.value, icon = icon, cancelled = !_notification.value)
        viewModelScope.launch {
            try {
                if (!isNameValid(habitName.value)) {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            message =  "Habit name should have atleast 3 characters"
                        )
                    )
                } else if (!isFreqValid(freq.value)) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Select atleast one day to perform this habit"
                            )
                        )
                }
                else {
                    useCases.createHabit(habit)
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