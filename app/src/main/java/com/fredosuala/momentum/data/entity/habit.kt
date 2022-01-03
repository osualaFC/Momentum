package com.fredosuala.momentum.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var frequency: List<String>,
    var creationDate: String,
    var score: Int,
    var cancelled: Boolean
)