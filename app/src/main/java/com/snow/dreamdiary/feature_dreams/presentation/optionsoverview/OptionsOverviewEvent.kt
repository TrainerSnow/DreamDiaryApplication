package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import android.net.Uri
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens

sealed class OptionsOverviewEvent {
    data class GoToScreen(val screen: DreamScreens) : OptionsOverviewEvent()

    object Backup: OptionsOverviewEvent()
    data class RestoreBackup(val data: Uri): OptionsOverviewEvent()
}
