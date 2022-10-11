package com.snow.dreamdiary.feature_dreams.presentation.addeditdream

sealed class AddEditDreamEvent{
    object Add : AddEditDreamEvent()
    object RequestAdd : AddEditDreamEvent()
    data class ChangeDescription(val value: String) : AddEditDreamEvent()
    data class ChangeAnnotation(val value: String): AddEditDreamEvent()

    data class ChangePersons(val value: String): AddEditDreamEvent()
    data class ChangeFeelings(val value: String): AddEditDreamEvent()
    data class ChangeLocations(val value: String): AddEditDreamEvent()

    data class ChangeComfortness(val value: Int): AddEditDreamEvent()

    data class ChangeDreamtAt(val value: Long): AddEditDreamEvent()

    object Cancel: AddEditDreamEvent()
    object DismissAddRequest : AddEditDreamEvent()
}
