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

    fun today() : Int {
        val dateTime = DateTime.now()
        return dateTime.dayOfWeek
    }

    fun getCurrentDateText() : String {
        val dateTime = DateTime.now()
        val dateTimeFormatter = DateTimeFormat.longDate().withLocale(Locale.getDefault())
        return dateTimeFormatter.print(dateTime)
    }

    fun getCurrentWeekDayText(context: Context) : String {
        val dayOfWeek = today() - 1
        val weekDaysArray = context.resources.getStringArray(R.array.WeekDays)
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