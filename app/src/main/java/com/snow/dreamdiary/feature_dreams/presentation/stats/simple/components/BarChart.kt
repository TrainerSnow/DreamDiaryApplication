package com.snow.dreamdiary.feature_dreams.presentation.stats.simple.components;

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private const val TAG = "BarChart"

@Composable
fun HorizontalBarChart(
    modifier: Modifier = Modifier,
    labels: List<String>,
    data: List<Int>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .horizontalScroll(rememberScrollState())
            .background(color = Color.Gray),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Log.e(TAG, "HorizontalBarChart: inside HorizontalBarChart with data = $data and labels = $labels", )
        if (data.isNotEmpty() && labels.isNotEmpty()) {
            val max = data.first()
            data.forEachIndexed { index, value ->
                val label = labels[index]
                val fraction = value.toFloat() / max.toFloat()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (fraction < 0.2F) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = label, textAlign = TextAlign.Center
                            )
                            Text(
                                text = value.toString(), textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(fraction)
                            .width(64.dp)
                            .background(Color.Green), contentAlignment = Alignment.Center
                    ) {
                        if (fraction >= 0.2F) {
                            Column() {
                                Text(
                                    text = label, textAlign = TextAlign.Center
                                )
                                Text(
                                    text = value.toString(), textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}