package com.snow.dreamdiary.common.util

object Arrayutil {

    fun sortArraysDependingOnFirst(
        arr1: List<String>,
        arr2: List<Int>,
        inverted: Boolean = false
    ): kotlin.Pair<List<String>, List<Int>>{
        val pairs: MutableList<Pair> = mutableListOf()
        arr1.forEachIndexed { index, item ->
            pairs.add(
                Pair(
                    item,
                    arr2[index]
                )
            )
        }

        if(inverted){
            pairs.sortByDescending { it.num }
        }else{
            pairs.sortBy { it.num }
        }

        val rarr1 = mutableListOf<String>()
        val rarr2 = mutableListOf<Int>()

        pairs.forEach {
            rarr1.add(it.string)
            rarr2.add(it.num)
        }

        return Pair(rarr1, rarr2)
    }

    private class Pair(
        var string: String, var num: Int
        ) : Comparable<Pair?> {

        override fun compareTo(other: Pair?): Int {
            if(other == null)
                return 1

            return this.num - other.num
        }
    }
}