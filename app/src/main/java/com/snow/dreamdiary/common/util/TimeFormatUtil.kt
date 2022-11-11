package com.snow.dreamdiary.common.util

import org.joda.time.DateTime
import org.joda.time.DateTimeField
import org.joda.time.DateTimeFieldType
import org.joda.time.DateTimeFieldType.*
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.util.*

object TimeFormatUtil {

    fun getTodayFormatted(): String {
        return getMillisDayFormatted(System.currentTimeMillis())
    }

    fun getMillisDayFormatted(millis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        val format = DateTimeFormat.forPattern("dd.MM.yyyy")
        val ld = LocalDate(millis)

        return ld.toString(format)
    }

    fun getMillisDayTimeFormatted(millis: Long): String {
        val time = Calendar.getInstance()
        return "${time.get(Calendar.DAY_OF_YEAR)}.${time.get(Calendar.MONTH) + 1}.${time.get(Calendar.YEAR)}_${time.get(Calendar.HOUR_OF_DAY)}-${time.get(Calendar.MINUTE)}-${time.get(Calendar.SECOND)}-${time.get(Calendar.MILLISECOND)}"
    }

}