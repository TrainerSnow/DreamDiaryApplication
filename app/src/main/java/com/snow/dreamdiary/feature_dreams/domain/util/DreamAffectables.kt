package com.snow.dreamdiary.feature_dreams.domain.util

import androidx.annotation.StringRes
import com.snow.dreamdiary.R

sealed class DreamAffectables(
    @StringRes val displayNameId: Int
){
    object Occurence: DreamAffectables(displayNameId = R.string.dream_occurence)
    object Comfortness: DreamAffectables(displayNameId = R.string.dream_comfortness)

}
