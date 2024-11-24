package com.example.povar.data.storage.util

import androidx.room.TypeConverter
import com.example.povar.domain.model.Ingredient

class CustomTypeConverters {

    @TypeConverter
    fun convertListStringToString(list: List<Ingredient>?) : String? {
        return list?.joinToString(separator = ";") { "${it.name}-${it.description}" }
    }

    @TypeConverter
    fun convertStringToListString(string: String?): List<Ingredient>? {
        return string?.split(";")?.mapNotNull { str ->
            val parts = str.split("-")
            if (parts.size == 2) {
                Ingredient(parts[0], parts[1])
            } else {
                null
            }
        }
    }
}