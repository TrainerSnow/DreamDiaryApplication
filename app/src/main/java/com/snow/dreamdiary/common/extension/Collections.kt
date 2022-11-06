package com.snow.dreamdiary.common.extension

fun <T> List<T>.average(selector: (T) -> Int): Double{
    var sum = 0

    this.forEach {
        sum += selector(it)
    }

    return sum.toDouble()/this.size.toDouble()
}