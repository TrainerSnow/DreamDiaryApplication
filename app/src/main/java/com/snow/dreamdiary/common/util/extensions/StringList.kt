package com.snow.dreamdiary.common.util.extensions

fun String.splitTrimmed(delimiter: String = ";"): List<String> {
    return this.split(delimiter).map {
        it.trim()
    }
}