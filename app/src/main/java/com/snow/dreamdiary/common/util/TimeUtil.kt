package com.snow.dreamdiary.common.util

import java.time.LocalDate
import java.time.temporal.ChronoField

object TimeUtil {

    fun millisFromLocalDate(date: LocalDate): Long{
        return date.getLong(ChronoField.EPOCH_DAY)*24*60*60*1000
    }

}