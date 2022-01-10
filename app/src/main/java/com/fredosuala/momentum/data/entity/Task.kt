package com.fredosuala.momentum.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.fredosuala.momentum.domain.util.CalenderUtil

@Entity(
        foreignKeys = [
                ForeignKey(
                        entity = Habit::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("habitId"),
                        onDelete = ForeignKey.CASCADE
                )
        ]
)
data class Task(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val habitId: Long,
        var status: Status,
        val date: String = CalenderUtil.getCurrentDateText()
        )

enum class Status {
        PENDING,
        MISSED,
        COMPLETED
}