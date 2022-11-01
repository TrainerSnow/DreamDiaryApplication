package com.snow.dreamdiary.feature_dreams.presentation.history

import com.snow.dreamdiary.common.util.TimeFormatUtil

data class HistoryState(
    val modifierView: Boolean = false,
    val dreamView: Boolean = true,
    val actualView: Boolean = true,
    val changedView: Boolean = false,

    val searchedValue: String = "",


    val timeStamps: List<Long> = listOf(),

    val earliestTimeStamp: Long = -1L
)
