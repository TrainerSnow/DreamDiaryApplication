package com.snow.dreamdiary.feature_dreams.domain.util

enum class DreamModifier{
    Person,
    Feeling,
    Location
    ;

    companion object{
        fun fromString(s: String): DreamModifier{
            return valueOf(s)
        }
    }
}
