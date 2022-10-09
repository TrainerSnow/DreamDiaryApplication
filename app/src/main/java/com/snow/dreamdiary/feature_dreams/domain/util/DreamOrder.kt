package com.snow.dreamdiary.feature_dreams.domain.util;

sealed class DreamOrder(val orderType: OrderType) {
    class Dreamed(orderType: OrderType): DreamOrder(orderType)
    class Created(orderType: OrderType): DreamOrder(orderType)
}