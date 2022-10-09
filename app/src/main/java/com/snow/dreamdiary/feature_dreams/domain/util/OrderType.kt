package com.snow.dreamdiary.feature_dreams.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
