package com.snow.dreamdiary.feature_dailysurvey.domain.util

import org.joda.time.DateTime
import java.util.*

object TimeUtil {

    fun thisDayStartInMillis(): Long {
        val now = DateTime()
        val today = now.toLocalDate()
        return today.toDateTimeAtStartOfDay().millis
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