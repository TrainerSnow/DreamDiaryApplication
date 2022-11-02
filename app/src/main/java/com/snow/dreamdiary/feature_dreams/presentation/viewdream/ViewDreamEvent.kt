package com.snow.dreamdiary.feature_dreams.presentation.viewdream

sealed class ViewDreamEvent{
    object ToggleDesc: ViewDreamEvent()
    object ToggleAnn: ViewDreamEvent()
    object TogglePerson: ViewDreamEvent()
    object ToggleFeeling: ViewDreamEvent()
    object ToggleLocation: ViewDreamEvent()
    object ToggleComf: ViewDreamEvent()

    object Delete: ViewDreamEvent()
}
