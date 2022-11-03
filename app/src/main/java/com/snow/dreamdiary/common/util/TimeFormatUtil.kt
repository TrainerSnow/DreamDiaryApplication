package com.snow.dreamdiary.common.util

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.util.*

object TimeFormatUtil {

    fun getTodayFormatted(): String {
        return getMillisFormatted(System.currentTimeMillis())
    }

    fun getMillisFormatted(millis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        val format = DateTimeFormat.forPattern("dd.MM.yyyy")
        val ld = LocalDate(millis)

        return ld.toString(format)
    }

}