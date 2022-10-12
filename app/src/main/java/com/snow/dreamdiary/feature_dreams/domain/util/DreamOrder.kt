package com.snow.dreamdiary.feature_dreams.domain.util

import androidx.annotation.StringRes
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.OrderType

sealed class DreamOrder(val orderType: OrderType, @StringRes val name: Int) {
    class Dreamed(orderType: OrderType) : DreamOrder(orderType, R.string.order_dreamed)
    class Created(orderType: OrderType) : DreamOrder(orderType, R.string.order_created)
}