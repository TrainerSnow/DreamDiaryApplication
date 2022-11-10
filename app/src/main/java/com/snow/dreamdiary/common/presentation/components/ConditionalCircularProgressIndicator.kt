package com.snow.dreamdiary.common.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun ConditionalCircularProgressIndicator(
    flag: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ){
        if(flag)
            content()
        else
            CircularProgressIndicator()
    }
}