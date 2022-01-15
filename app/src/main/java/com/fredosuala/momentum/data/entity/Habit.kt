package com.fredosuala.momentum.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fredosuala.momentum.domain.model.HabitDomain

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var frequency: List<String>,
    var reminder : String,
    var icon : Int,
    var tracker : String? = null,
    var score: Int = 0,
    var cancelled: Boolean = false,
    var completed: Int = 0,
    var missed : Int = 0,
    var creationDate: Long = System.currentTimeMillis()
) {
//    fun toDomain() : HabitDomain {
//        return HabitDomain(
//            id = id,
//            name = name,
//            frequency = frequency,
//            creationDate = creationDate,
//            score = score,
//            cancelled = cancelled,
//            completed = completed,
//            missed = missed,
//            reminder = reminder,
//            icon = icon
//        )
//    }
}