package com.snow.dreamdiary.common.util

import java.util.jar.Attributes.Name

sealed interface LogicGate{
    fun <T> eval(
        values: List<T>,
        condition: (T) -> Boolean
    ): Boolean

    object And: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            values.forEach{
                if(!condition(it)){
                    return false
                }
            }
            return true
        }
    }

    object Or: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            values.forEach{
                if(condition(it)){
                    return true
                }
            }
            return false
        }
    }

    object Xor: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            var trueInputs = 0
            values.forEach{
                if(condition(it)){
                    trueInputs += 1
                }
            }
            return trueInputs % 2 != 0
        }
    }

    object Nand: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return And.eval(values, condition).not()
        }
    }

    object Nor: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return Or.eval(values, condition).not()
        }
    }

    object XNor: LogicGate{
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return Xor.eval(values, condition).not()
        }
    }

    companion object{
        fun fromName(s: String): LogicGate{
            return when (s) {
                And.javaClass.name -> And
                Or.javaClass.name -> Or
                Xor.javaClass.name -> Xor
                Nand.javaClass.name -> Nand
                Nor.javaClass.name -> Nor
                XNor.javaClass.name -> XNor
                else -> throw IllegalArgumentException("No LogicGate found for name '$s'")
            }
        }
    }
}