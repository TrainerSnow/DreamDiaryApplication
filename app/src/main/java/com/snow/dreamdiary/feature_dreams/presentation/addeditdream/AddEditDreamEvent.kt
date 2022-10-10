package com.snow.dreamdiary.feature_dreams.presentation.addeditdream

import com.snow.dreamdiary.feature_dreams.domain.model.Dream

sealed class AddEditDreamEvent{
    data class Add(val dream: Dream): AddEditDreamEvent()
    object Cancel: AddEditDreamEvent()
}
