package com.fredosuala.momentum.domain.util

import android.content.Context
import android.util.Log
import com.fredosuala.momentum.R
import com.fredosuala.momentum.domain.model.TaskDomain
import com.google.gson.Gson
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object CalenderUtil {

    private fun today() : Int {
        val dateTime = DateTime.now()
        return dateTime.dayOfWeek
    }

    fun getCurrentDateText() : String {
        val dateTime = DateTime.now()
        val dateTimeFormatter = DateTimeFormat.longDate().withLocale(Locale.getDefault())
        return dateTimeFormatter.print(dateTime)
    }

    fun getCurrentWeekDayText() : String {
        val dayOfWeek = today() - 1
        val weekDaysArray = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        return weekDaysArray[dayOfWeek]
    }

    fun isDuplicateTask(task : TaskDomain, list : List<TaskDomain>) : Boolean {
        for(item in list) {
          //  Log.i("TAG", "isDuplicateTask: ${item.habitId} ${task.habitId}  ")
            if(item.habitId == task.habitId) return true
        }
        return false
    }

}