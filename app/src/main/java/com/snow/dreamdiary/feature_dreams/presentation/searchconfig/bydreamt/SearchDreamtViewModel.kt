package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchDreamtViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(SearchDreamtState())
    val state: State<SearchDreamtState> = _state

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

}