package com.snow.dreamdiary.feature_dreams.data.source;

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONObject

class TypeConverters {

    @TypeConverter
    fun fromString(string: String): List<String>{
        val obj = JSONObject(string)
        val arr = mutableListOf<String>()
        val jsonArr = obj.getJSONArray("values")

        for(i in 0 until jsonArr.length()){
            arr.add(jsonArr.getString(i))
        }

        return arr
    }

    @TypeConverter
    fun listToString(list: List<String>): String{
        val obj = JSONObject()
        val arr = JSONArray()

        list.forEach {
            arr.put(it)
        }

        obj.put("values", arr)

        return obj.toString()
    }

}