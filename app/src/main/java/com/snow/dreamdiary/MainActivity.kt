package com.snow.dreamdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.snow.dreamdiary.feature_dreams.presentation.MainDreamScreen
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DreamDiaryApplicationTheme {
                MainDreamScreen()
            }
        }
    }
}