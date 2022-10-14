package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun OptionsItem(
    text: String,
    icon: ImageVector,
    cd: String = text,
    onClick: () -> Unit,
    num: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = getRandomGradientType(
                    getColorPair(isSystemInDarkTheme(), num)
                )
            )
            .clickable { onClick() }
            .fillMaxSize()
            .aspectRatio(1F)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp),
                imageVector = icon,
                contentDescription = cd,
                tint = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

private fun getColorPair(dark: Boolean, num: Int): List<Color> =
    if (dark) darkGradients[num % darkGradients.size] else lightGradients[num % lightGradients.size]


val darkGradients: List<List<Color>> = listOf(
    listOf(
        Color(0xFF0f0c29),
        Color(0xFF24243e)
    ),
    listOf(
        Color(0xFF141E30),
        Color(0xFF243B55)
    ),
    listOf(
        Color(0xFF16222A),
        Color(0xFF3A6073)
    ),
    listOf(
        Color(0xFF000000),
        Color(0xFF434343)
    ),
    listOf(
        Color(0xFF232526),
        Color(0xFF414345)
    ),
    listOf(
        Color(0xFF41295a),
        Color(0xFF2F0743)
    ),
)

val lightGradients: List<List<Color>> = listOf(
    listOf(
        Color(0xFFddd6f3),
        Color(0xFFfaaca8)
    ),
    listOf(
        Color(0xFFee9ca7),
        Color(0xFFffdde1)
    ),
    listOf(
        Color(0xFFDAE2F8),
        Color(0xFFD6A4A4)
    ),
    listOf(
        Color(0xFFC9D6FF),
        Color(0xFFE2E2E2)
    ),
    listOf(
        Color(0xFFA1FFCE),
        Color(0xFFFAFFD1)
    ),
    listOf(
        Color(0xFF1CD8D2),
        Color(0xFF93EDC7)
    ),
)

private fun getRandomGradientType(colors: List<Color>): Brush {
    return listOf(
        Brush.horizontalGradient(colors),
        Brush.linearGradient(colors),
        Brush.verticalGradient(colors)
    ).random()
}
