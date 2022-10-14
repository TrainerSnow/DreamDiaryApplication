package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchModeViewModel @Inject constructor(

) : ViewModel() {

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: SearchModeEvent) {
        when (event) {
            is SearchModeEvent.OpenScreen -> {

            }
            SearchModeEvent.OpenSearchComfortness -> {
                viewModelScope.launch {
                    _actionFlow.emit(
                        UIEvent.GoToScreen(
                            screen = DreamScreens.SearchComfortnessScreen()
                                .withMode(DreamSearchModes.ByComfortness())
                        )
                    )
                }
            }
            SearchModeEvent.OpenSearchModifiers -> {
                viewModelScope.launch {
                    _actionFlow.emit(
                        UIEvent.GoToScreen(
                            screen = DreamScreens.SearchModifierScreen()
                                .withMode(DreamSearchModes.ByModifier())
                        )
                    )
                }
            }
            SearchModeEvent.OpenSearchDreamt -> {
                viewModelScope.launch {
                    _actionFlow.emit(
                        UIEvent.GoToScreen(
                            screen = DreamScreens.SearchDreamtScreen()
                                .withMode(DreamSearchModes.ByDreamt())
                        )
                    )
                }
            }
        }
    }

    sealed class UIEvent {
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }

}