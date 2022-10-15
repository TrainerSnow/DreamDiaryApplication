package com.snow.dreamdiary.feature_dreams.domain.util

import com.snow.dreamdiary.common.util.LogicGate
import org.json.JSONArray
import org.json.JSONObject

private const val KEY_MODE_NAME = "modename"

private const val KEY_MODIFIER = "modifier"
private const val KEY_VALUES = "values"
private const val KEY_VALUE = "value"
private const val KEY_GATE = "gate"

private const val KEY_FROM = "fromval"
private const val KEY_TO = "toval"

private const val KEY_FROM_TIME = "totime"
private const val KEY_TO_TIME = "fromtime"


sealed class DreamSearchModes {
    data class ByModifier(
        val values: HashMap<String, DreamModifier> = hashMapOf(),
        val gate: LogicGate = LogicGate.And
    ) : DreamSearchModes() {
        fun toJson(): JSONObject {
            val valuesArr = JSONArray()
            this.values.forEach {
                valuesArr.put(
                    JSONObject()
                        .put(KEY_VALUE, it.key)
                        .put(KEY_MODIFIER, it.value)
                )
            }
            return JSONObject().apply {
                put(KEY_MODE_NAME, this@ByModifier.javaClass.name)
                put(KEY_VALUES, valuesArr)
                put(KEY_GATE, gate.javaClass.name)
            }
        }
    }

    data class ByComfortness(
        val fromVal: Byte = 0,
        val toVal: Byte = 0
    ) : DreamSearchModes() {
        fun toJson(): JSONObject {
            return JSONObject().apply {
                put(KEY_MODE_NAME, this@ByComfortness.javaClass.name)
                put(KEY_FROM, fromVal)
                put(KEY_TO, toVal)
            }
        }
    }

    data class ByDreamt(
        val fromTime: Long = 0,
        val toTime: Long = 0
    ) : DreamSearchModes() {
        fun toJson(): JSONObject {
            return JSONObject().apply {
                put(KEY_MODE_NAME, this@ByDreamt.javaClass.name)
                put(KEY_FROM_TIME, fromTime)
                put(KEY_TO_TIME, toTime)
            }
        }
    }

    companion object {
        fun fromJson(obj: JSONObject): DreamSearchModes {
            return when (obj.get(KEY_MODE_NAME)) {
                ByModifier().javaClass.name -> {
                    val values: HashMap<String, DreamModifier> = hashMapOf()
                    val jArr = obj.getJSONArray(KEY_VALUES)
                    for (i in 0 until jArr.length()) {
                        values[
                                jArr.getJSONObject(i).getString(KEY_VALUES)
                        ] =
                            DreamModifier.valueOf(jArr.getJSONObject(i).getString(KEY_MODIFIER))
                    }

                    ByModifier(
                        values = values,
                        gate = LogicGate.fromName(obj.getString(KEY_GATE))
                    )
                }

                ByComfortness().javaClass.name -> {
                    ByComfortness(
                        fromVal = obj.getInt(KEY_FROM).toByte(),
                        toVal = obj.getInt(KEY_TO).toByte()
                    )
                }

                ByDreamt().javaClass.name -> {
                    ByDreamt(
                        fromTime = obj.getLong(KEY_FROM_TIME),
                        toTime = obj.getLong(KEY_TO_TIME)
                    )
                }
                else -> throw IllegalArgumentException(
                    "No DreamSearchMode found for name '${
                        obj.get(
                            KEY_MODE_NAME
                        )
                    }'"
                )
            }
        }
    }
}
