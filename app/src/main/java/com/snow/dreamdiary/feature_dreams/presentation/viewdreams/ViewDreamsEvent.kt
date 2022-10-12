package com.snow.dreamdiary.feature_dreams.presentation.viewdreams

import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

sealed class ViewDreamsEvent{
    data class EditDream(val dreamId: Int): ViewDreamsEvent()
    data class DeleteDream(val dreamId: Int): ViewDreamsEvent()

    data class RenewOrder(val dreamOrder: DreamOrder): ViewDreamsEvent()

    object NextDream: ViewDreamsEvent()
    object RecentDream: ViewDreamsEvent()

    object OpenOrderMenu: ViewDreamsEvent()
    object CloseOrderMenu: ViewDreamsEvent()

}
