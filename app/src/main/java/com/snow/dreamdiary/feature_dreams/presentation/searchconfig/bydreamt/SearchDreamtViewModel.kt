package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDreamtViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(SearchDreamtState())
    val state: State<SearchDreamtState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: SearchDreamtEvent){
        when (event) {
            is SearchDreamtEvent.ChangeDreamtFrom -> {
                _state.value = state.value.copy(
                    mode = state.value.mode.copy(
                        fromTime = event.value
                    )
                )
            }
            is SearchDreamtEvent.ChangeDreamtTo -> {
                _state.value = state.value.copy(
                    mode = state.value.mode.copy(
                        toTime = event.value
                    )
                )
            }
            SearchDreamtEvent.StartSearch -> {
                viewModelScope.launch {
                    _actionFlow.emit(
                        UIEvent.GoToScreen(
                            DreamScreens.ViewSearchedDreamsScreen().withMode(_state.value.mode)
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