package com.fredosuala.momentum.domain.model

data class HabitDomain(
    val id: Long = 0,
    var name: String,
    var frequency: List<String>,
    var reminder : String,
    var icon: Int,
    var creationDate: Long,
    var score: Int,
    var cancelled: Boolean,
    var completed: Int,
    var missed : Int
)
