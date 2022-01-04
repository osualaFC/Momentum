package com.fredosuala.momentum.domain.usecases

import com.fredosuala.momentum.domain.repository.Repository
import javax.inject.Inject

class ClearTable @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.nukeTable()
    }
}