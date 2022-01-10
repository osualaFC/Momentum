package com.fredosuala.momentum.presentation.home.habitdetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fredosuala.momentum.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
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

    private val _state = mutableStateOf(HabitDetailState())
    val state : State<HabitDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                        message = "Select atleast one day to perform this habit"
                    )
                )
            }
        }
    }
}



sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
}