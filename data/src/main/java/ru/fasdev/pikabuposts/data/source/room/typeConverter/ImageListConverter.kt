package ru.fasdev.pikabuposts.data.source.room.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageListConverter
{
    @TypeConverter
    fun fromListToString(value: List<String>?): String? {
        if (value == null)
            return null

        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toListFronString(value: String?): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(value, type)
    }
}