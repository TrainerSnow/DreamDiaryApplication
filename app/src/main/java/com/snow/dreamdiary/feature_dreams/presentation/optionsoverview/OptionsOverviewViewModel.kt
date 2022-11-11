package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.usecase.SurveyUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "OptionsOverviewViewMode"

@HiltViewModel
class OptionsOverviewViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases,
    private val surveyUseCases: SurveyUseCases
) : ViewModel() {

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: OptionsOverviewEvent) {
        when (event) {
            is OptionsOverviewEvent.GoToScreen -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.GoToScreen(event.screen))
                }
            }
            OptionsOverviewEvent.Backup -> {

            }
            is OptionsOverviewEvent.RestoreBackup -> {

            }
        }
    }

    sealed class UIEvent {
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }

}