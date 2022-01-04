package com.fredosuala.momentum.presentation.util

import android.content.Context

class ResourceUtil(private val context: Context) {

    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}