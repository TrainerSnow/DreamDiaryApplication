package com.snow.dreamdiary.common.util

import java.time.LocalDate
import java.time.temporal.ChronoField
import java.util.*

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

}