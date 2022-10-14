package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import com.snow.dreamdiary.common.util.OrderType
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

data class ViewDreamsState(
    val dreams: List<Dream> = emptyList(),
    val sortingOrder: DreamOrder = DreamOrder.Dreamed(OrderType.Descending),
    val isOrderMenuExpanded: Boolean = false,
    val currentDreamIndex: Int = 0

)
