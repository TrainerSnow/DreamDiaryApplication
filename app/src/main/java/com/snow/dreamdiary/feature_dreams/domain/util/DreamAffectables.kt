package com.snow.dreamdiary.feature_dreams.domain.util

sealed class DreamAffectables{
    object Occurence: DreamAffectables()
    object Comfortness: DreamAffectables()
}
