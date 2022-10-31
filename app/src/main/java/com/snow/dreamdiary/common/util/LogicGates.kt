package com.snow.dreamdiary.common.util

import com.snow.dreamdiary.R

sealed interface LogicGate {
    fun <T> eval(
        values: List<T>,
        condition: (T) -> Boolean
    ): Boolean

    val nameRes: Int

    object And : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            values.forEach {
                if (!condition(it)) {
                    return false
                }
            }
            return true
        }

        override val nameRes: Int = R.string.gate_and
    }

    object Or : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            values.forEach {
                if (condition(it)) {
                    return true
                }
            }
            return false
        }

        override val nameRes: Int = R.string.gate_or
    }

    object Xor : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            var trueInputs = 0
            values.forEach {
                if (condition(it)) {
                    trueInputs += 1
                }
            }
            return trueInputs % 2 != 0
        }

        override val nameRes: Int = R.string.gate_xor
    }

    object Nand : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return And.eval(values, condition).not()
        }

        override val nameRes: Int = R.string.gate_nand
    }

    object Nor : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return Or.eval(values, condition).not()
        }

        override val nameRes: Int = R.string.gate_nor
    }

    object XNor : LogicGate {
        override fun <T> eval(values: List<T>, condition: (T) -> Boolean): Boolean {
            return Xor.eval(values, condition).not()
        }

        override val nameRes: Int = R.string.gate_xnor
    }

    companion object {

        fun fromName(s: String): LogicGate {
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

        val logicGates: List<LogicGate> = listOf(
            And,
            Or,
            Xor,
            Nand,
            Nor,
            XNor
        )
    }
}