package com.snow.dreamdiary.feature_dreams.presentation.history.components

import android.text.TextPaint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

private const val TAG = "LineChart"

@Composable
fun TimeStampLineGraphActual(
    yLabel: String,
    xLabel: String,
    timeStamps: List<Long>,
    timeStart: Long,
    axisColor: Color = Color.Black,
    dotColor: Color = Color.Red,
    lineColor: Color = Color.Red
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        xAxis(
            label = xLabel,
            color = axisColor
        )
        yAxis(
            label = yLabel,
            color = axisColor
        )
        timePointsActual(
            timeStamps = timeStamps,
            timeStart = timeStart,
            lineColor = lineColor,
            dotColor = dotColor
        )
    }
}

@Composable
fun TimeStampLineGraphDerivative(
    yLabel: String,
    xLabel: String,
    timeStamps: List<Long>,
    timeStart: Long,
    interval: Long,
    yMaxValue: Int,
    axisColor: Color = Color.Black,
    dotColor: Color = Color.Red,
    lineColor: Color = Color.Red
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        xAxis(
            label = xLabel,
            color = axisColor
        )
        yAxis(
            label = yLabel,
            color = axisColor
        )
        timePointsDerivative(
            timeStamps = timeStamps,
            timeStart = timeStart,
            lineColor = lineColor,
            dotColor = dotColor,
            interval = interval,
            yMaxValue = yMaxValue
        )
    }
}

private fun DrawScope.timePointsActual(
    timeStamps: List<Long>,
    timeStart: Long,
    lineColor: Color,
    dotColor: Color
) {
    val width = size.width
    val height = size.height
    val now = System.currentTimeMillis()

    val points: MutableList<Offset> = mutableListOf()

    timeStamps.forEachIndexed { index, timestamp ->
        val y = convert(
            original = (1).rangeTo(timeStamps.size.toLong()),
            target = (0).rangeTo(height.toLong()),
            number = (index + 1).toLong()
        )
        Log.e(TAG, "timePointsActual: Creating X value", )
        Log.e(TAG, "timePointsActual: Original range: ${timeStart.rangeTo(now)}", )
        Log.e(TAG, "timePointsActual: Target Range: ${(0).rangeTo(width.toLong())}", )
        Log.e(TAG, "timePointsActual: Number Input: $timestamp", )
        val x = convert(
            original = timeStart.rangeTo(now),
            target = (0).rangeTo(width.toLong()),
            number = timestamp
        )
        Log.e(TAG, "timePointsActual: Created coordinate: X = ${x.toFloat()}, Y = ${size.height - y.toFloat()}", )
        points.add(
            index = index,
            element = Offset(
                x = x.toFloat(),
                y = size.height - y.toFloat()
            )
        )
    }

    points.forEach {
        drawCircle(
            color = dotColor,
            radius = 4F,
            center = it
        )
    }

    drawPoints(
        points = points,
        pointMode = PointMode.Polygon,
        color = lineColor
    )
}

private fun DrawScope.timePointsDerivative(
    interval: Long,
    timeStamps: List<Long>,
    timeStart: Long,
    lineColor: Color,
    dotColor: Color,
    yMaxValue: Int
) {
    val width = size.width
    val height = size.height
    var lastStoppedIndex = 0

    val now = System.currentTimeMillis()
    val sections = ceil(((now - timeStart) / interval).toDouble()).toInt()

    val points: MutableList<Offset> = mutableListOf()

    for (i in 0 until sections) {
        val acceptedRange = (timeStart + (i * interval))..(timeStart + (i * interval) + interval)
        var numDreams = 0

        while (
            try {
                acceptedRange.contains(
                    timeStamps[lastStoppedIndex]
                )
            } catch (e: IndexOutOfBoundsException) {
                break
            }
        ) {
            lastStoppedIndex += 1
            numDreams += 1
        }

        val y = convert(
            original = (0).rangeTo(yMaxValue.toLong()),
            target = (0).rangeTo(height.toLong()),
            number = numDreams.toLong()
        )
        val x = convert(
            original = timeStart.rangeTo(now),
            target = (0).rangeTo(width.toLong()),
            number = now + (i * interval) + (interval / 2)
        )

        // TODO: IDK why -1000 is needed but it works
        points.add(
            index = i,
            element = Offset(
                x = x.toFloat() - 1000,
                y = size.height - y.toFloat()
            )
        )
    }

    points.forEach {
        drawCircle(
            color = dotColor,
            radius = 4F,
            center = it
        )
    }

    drawPoints(
        points = points,
        pointMode = PointMode.Polygon,
        color = lineColor
    )
}


private fun DrawScope.yAxis(
    label: String,
    color: Color
) {
    drawLine(
        color = color,
        strokeWidth = 2.dp.toPx(),
        start = Offset(x = 0F, y = 0f),
        end = Offset(x = 0F, y = size.height)
    )

    rotate(
        degrees = 270F
    ) {
        drawIntoCanvas {
            val native = it.nativeCanvas
            val paint = TextPaint()
            paint.textSize = 30F

            native.drawText(
                label,
                size.width - (size.width / 10),
                (size.height / 120),
                paint
            )
        }
    }
}

private fun DrawScope.xAxis(
    label: String,
    color: Color
) {
    drawLine(
        color = color,
        strokeWidth = 2.dp.toPx(),
        start = Offset(x = 0F, y = size.height),
        end = Offset(x = size.width, y = size.height)
    )

    drawIntoCanvas {
        val native = it.nativeCanvas
        val paint = TextPaint()
        paint.textSize = 30F

        native.drawText(
            label,
            size.width - (size.width / 10),
            size.height - (size.height / 80),
            paint
        )
    }
}

private fun convert(number: Long, original: LongRange, target: LongRange): Int {
    val ratio = (number - original.first).toFloat() / (original.last - original.first)
    return (ratio * (target.last - target.first)).toInt()
}