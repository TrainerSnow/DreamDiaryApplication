package com.snow.dreamdiary.feature_dreams.domain.util

sealed class DreamModifiers{
    object Person: DreamModifiers()
    object Feeling: DreamModifiers()
    object Location: DreamModifiers()
}
