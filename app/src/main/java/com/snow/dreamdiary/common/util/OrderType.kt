package com.snow.dreamdiary.common.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
