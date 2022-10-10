package com.snow.dreamdiary.feature_dreams.presentation.addeditdream

import com.snow.dreamdiary.feature_dreams.domain.model.Dream

sealed class AddEditDreamEvent{
    data class Add(val dream: Dream): AddEditDreamEvent()
    data class ChangeDescription(val value: String): AddEditDreamEvent()
    data class ChaAnnotation(val value: String): AddEditDreamEvent()

    data class ChangePersons(val value: List<String>): AddEditDreamEvent()
    data class ChangeFeelings(val value: List<String>): AddEditDreamEvent()
    data class ChangeLocations(val value: List<String>): AddEditDreamEvent()

    data class ChangeComfortness(val value: Int): AddEditDreamEvent()

    data class ChangeDreamtAt(val value: Long): AddEditDreamEvent()

    object Cancel: AddEditDreamEvent()
}
