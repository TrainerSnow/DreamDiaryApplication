package com.snow.dreamdiary.common.util

import android.icu.text.SimpleDateFormat
import java.util.*

object TimeFormatUtil {

    fun getTodayFormatted(): String {
        return getMillisFormatted(System.currentTimeMillis())
    }

    fun getMillisFormatted(millis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        val format = SimpleDateFormat("dd.MM.yyyy", Locale.US)

        return format.format(calendar.time)
    }

}