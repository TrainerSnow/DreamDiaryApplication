package com.snow.dreamdiary.feature_dreams.presentation.addeditdream

import androidx.compose.ui.graphics.Color
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

sealed class AddEditDreamEvent{
    data class Add(val dream: Dream): AddEditDreamEvent()
    data class ChangeDescription(val value: String): AddEditDreamEvent()
    data class ChangeAnnotation(val value: String): AddEditDreamEvent()

    data class ChangePersons(val value: String): AddEditDreamEvent()
    data class ChangeFeelings(val value: String): AddEditDreamEvent()
    data class ChangeLocations(val value: String): AddEditDreamEvent()

    data class ChangeComfortness(val value: Int): AddEditDreamEvent()

    data class ChangeDreamtAt(val value: Long): AddEditDreamEvent()

    data class ChangeDreamColor(val value: Color): AddEditDreamEvent()

    object Cancel: AddEditDreamEvent()
}
