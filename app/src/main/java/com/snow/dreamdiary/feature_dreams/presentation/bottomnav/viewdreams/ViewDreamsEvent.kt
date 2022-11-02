package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

sealed class ViewDreamsEvent {

    data class RenewOrder(val dreamOrder: DreamOrder) : ViewDreamsEvent()

    object ToggleOrderMenu : ViewDreamsEvent()

}
