package com.fredosuala.momentum.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converter {
    @TypeConverter
    fun fromString(str: String): List<String> {
        return str.split(",").map { it }
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }
}