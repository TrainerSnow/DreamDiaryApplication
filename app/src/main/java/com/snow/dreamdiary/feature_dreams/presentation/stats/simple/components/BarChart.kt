package com.snow.dreamdiary.feature_dreams.presentation.stats.simple.components;

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun HorizontalBarChart(
    modifier: Modifier = Modifier,
    labels: List<String>,
    data: List<Int>,
    barColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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
                            .clip(RoundedCornerShape(8.dp))
                            .background(barColor),
                        contentAlignment = Alignment.Center

                    ) {
                        if (fraction >= 0.2F) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = label,
                                    textAlign = TextAlign.Center,
                                    color = contentColorFor(backgroundColor = barColor)
                                )
                                Text(
                                    text = value.toString(),
                                    textAlign = TextAlign.Center,
                                    color = contentColorFor(backgroundColor = barColor)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}