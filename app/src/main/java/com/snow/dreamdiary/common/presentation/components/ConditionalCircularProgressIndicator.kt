package com.snow.dreamdiary.common.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun ConditionalCircularProgressIndicator(
    flag: Boolean,
    content: () -> Unit
) {
    if(flag)
        content()
    else
        CircularProgressIndicator()
}