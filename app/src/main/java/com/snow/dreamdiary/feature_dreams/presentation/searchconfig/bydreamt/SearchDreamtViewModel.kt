package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness.SearchComfortnessViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SearchDreamtViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(SearchDreamtState())
    val state: State<SearchDreamtState> = _state

    private val _actionFlow = MutableSharedFlow<SearchComfortnessViewModel.UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: SearchDreamtEvent){
        when (event) {
            is SearchDreamtEvent.ChangeDreamtFrom -> {
                _state.value = state.value.copy(
                    dreamtFrom = event.value
                )
            }
            is SearchDreamtEvent.ChangeDreamtTo -> {
                _state.value = state.value.copy(
                    dreamtTo = event.value
                )
            }
            SearchDreamtEvent.StartSearch -> {

            }
        }
    }

    sealed class UIEvent {

    }

}