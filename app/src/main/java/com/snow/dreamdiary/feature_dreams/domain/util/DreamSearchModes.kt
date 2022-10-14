package com.snow.dreamdiary.feature_dreams.domain.util

sealed class DreamSearchModes{
    object ByModifier: DreamSearchModes()
    object ByComfortness: DreamSearchModes()
    object ByDreamt: DreamSearchModes()
    object ByCreated: DreamSearchModes()
}
