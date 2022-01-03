package com.fredosuala.momentum.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fredosuala.momentum.data.dao.Moment2mDao
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.Task

@Database(entities = [Habit::class, Task::class], version = 1)
@TypeConverters(Converter::class)
abstract class Moment2mDatabase(): RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "moment2m"
    }

    abstract val dao : Moment2mDao
}