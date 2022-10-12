package com.snow.dreamdiary.common.util

import androidx.annotation.StringRes
import com.snow.dreamdiary.R

sealed class OrderType(@StringRes val name: Int) {
    object Ascending : OrderType(R.string.order_ascending)
    object Descending : OrderType(R.string.order_descending)
}
