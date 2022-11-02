package com.snow.dreamdiary.common.util

import org.joda.time.DateTime
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtil {

    fun millisFromDateDescription(year: Int, month: Int, dayOfMonth: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        return calendar.timeInMillis
    }

    fun currentYear(): Int {
        val calendar = Calendar.getInstance()

        return calendar.get(Calendar.YEAR)
    }

    fun currentMonthOfYear(): Int {
        val calendar = Calendar.getInstance()

        return calendar.get(Calendar.MONTH)
    }

    fun currentDayOfMonth(): Int {
        val calendar = Calendar.getInstance()

        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun thisDayStartInMillis(): Long {
        val now = DateTime()
        val today = now.toLocalDate()
        return today.toDateTimeAtStartOfDay().millis
    }

    fun dayRangeFromMillis(millisInDay: Long): LongRange{
        val dayNum = TimeUnit.MILLISECONDS.toDays(millisInDay)
        val millisSinceStartOfDay = dayNum * 24 * 60 * 60 * 1000
        val millisAtEndofDay = millisSinceStartOfDay + (24 * 60 * 60 * 1000) - 1

        return millisSinceStartOfDay..millisAtEndofDay
    }

    fun millisToDayNum(millis: Long): Long{
        return TimeUnit.MILLISECONDS.toDays(millis)
    }

}