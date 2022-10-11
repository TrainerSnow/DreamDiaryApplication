package com.snow.dreamdiary.feature_dailysurvey.domain.util

import java.util.*

object TimeUtil {

    fun thisDayStartInMillis(): Long {
        val now = System.currentTimeMillis()
        val dayStart = millisSinceStartOfToday() - now
        return dayStart
    }

    private fun millisSinceStartOfToday(): Long {
        val date: Calendar = GregorianCalendar()
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0)
        date.set(Calendar.MINUTE, 0)
        date.set(Calendar.SECOND, 0)
        date.set(Calendar.MILLISECOND, 0)
        return Date().time - date.time.time
    }

}