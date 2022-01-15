package com.fredosuala.momentum.data.dao

import androidx.room.*
import com.fredosuala.momentum.data.entity.Habit
import com.fredosuala.momentum.data.entity.HabitWithTasks
import com.fredosuala.momentum.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface Moment2mDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabit(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task : Task)

    @Query("SELECT * FROM habit")
    suspend fun getAllHabit() : List<Habit>

    @Transaction
    @Query("SELECT * FROM habit")
    fun getAllHabitsWithTasks() : Flow<List<HabitWithTasks>>

    @Transaction
    @Query("SELECT * FROM habit wHERE id =:id")
    fun getHabitById(id: Long): Flow<Habit>

    @Update
    suspend fun updateHabit(habit : Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("DELETE  FROM habit")
    suspend fun nukeTable()
}