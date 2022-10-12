package com.snow.dreamdiary.feature_dreams.presentation.viewdreams

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

sealed class ViewDreamsEvent{
    data class EditDream(val dream: Dream): ViewDreamsEvent()
    data class DeleteDream(val dream: Dream): ViewDreamsEvent()

    data class RenewOrder(val dreamOrder: DreamOrder): ViewDreamsEvent()

    object NextDream: ViewDreamsEvent()
    object RecentDream: ViewDreamsEvent()

    object ToggleOrderMenu: ViewDreamsEvent()

}
