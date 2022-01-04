package com.fredosuala.momentum.domain.usecases

data class UseCases(
    val clearTable: ClearTable,
    val createHabit: CreateHabit,
    val deleteHabit: DeleteHabit,
    val generateTodayTasks: GenerateTodayTasks,
    val getHabit: GetHabit,
    val updateHabit: UpdateHabit
)
