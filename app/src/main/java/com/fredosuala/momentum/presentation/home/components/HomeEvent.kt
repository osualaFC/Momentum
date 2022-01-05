package com.fredosuala.momentum.presentation.home.components

sealed class HomeEvent{
   // data class ViewTaskDetails(val id: Long): HomeEvent()
    object SaveTask: HomeEvent()
}