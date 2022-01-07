package com.fredosuala.momentum.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, V, R> LiveData<T>.combineWith(
    liveData: LiveData<V>,
    block: (T, V) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        this.value?.let { first ->
            liveData.value?.let { second ->
                result.value = block(first, second)
            }
        }
    }
    result.addSource(liveData) {
        this.value?.let { first ->
            liveData.value?.let { second ->
                result.value = block(first, second)
            }
        }
    }

    return result
}