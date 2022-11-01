package com.snow.dreamdiary.feature_dreams.domain.util

import org.joda.time.DateTime

object TimeUtil {

    fun thisDayStartInMillis(): Long {
        val now = DateTime()
        val today = now.toLocalDate()
        return today.toDateTimeAtStartOfDay().millis
    }
}