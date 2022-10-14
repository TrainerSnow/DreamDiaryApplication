package com.snow.dreamdiary.feature_dreams.domain.util

sealed class DreamModifier{
    object Person: DreamModifier()
    object Feeling: DreamModifier()
    object Location: DreamModifier()
}
