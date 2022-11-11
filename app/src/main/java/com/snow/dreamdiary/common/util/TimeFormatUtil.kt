package com.snow.dreamdiary.common.util

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
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        val format = DateTimeFormat.forPattern("dd.MM.yyyy_hh:mm:ss")
        val ld = LocalDate(millis)

        return ld.toString(format)
    }

}