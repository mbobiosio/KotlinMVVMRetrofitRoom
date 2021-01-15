package com.mbobiosio.countries.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mbobiosio.countries.model.Currencies


/**
 * Converter for converting custom type [List] of <T> into a type Room knows how to save into the database
 */
class Converter {
    var gson = Gson()

    @TypeConverter
    fun currencyToString(currencies: List<Currencies>): String {
        return gson.toJson(currencies)
    }

    @TypeConverter
    fun stringToCurrency(data: String): List<Currencies> {
        val listType = object : TypeToken<List<Currencies>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringListToString(callingCodes: List<String>) : String{
        return gson.toJson(callingCodes)
    }

    @TypeConverter
    fun stringToStringList(data: String) : List<String> {
        val listType = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}