package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.common.util.OrderType
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDreamsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(
        dreamOrder: DreamOrder = DreamOrder.Dreamed(OrderType.Descending)
    ): Flow<List<Dream>> {
        return repository.getDreams().map { dreams ->
            when (dreamOrder.orderType) {
                is OrderType.Ascending -> {
                    when (dreamOrder) {
                        is DreamOrder.Dreamed -> dreams.sortedBy { it.dreamtAt }
                        is DreamOrder.Created -> dreams.sortedBy { it.createdAt }
                        is DreamOrder.Comfortness -> dreams.sortedBy { it.comfortness }
                    }
                }
                is OrderType.Descending -> {
                    when (dreamOrder) {
                        is DreamOrder.Dreamed -> dreams.sortedByDescending { it.dreamtAt }
                        is DreamOrder.Created -> dreams.sortedByDescending { it.createdAt }
                        is DreamOrder.Comfortness -> dreams.sortedByDescending { it.comfortness }
                    }
                }
            }
        }
    }
}