package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchComfortnessViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(SearchComfortnessState())
    val state: State<SearchComfortnessState> = _state

    fun onEvent(event: SearchComfortnessEvent){
        when (event) {
            is SearchComfortnessEvent.ChangeFromValue -> {
                _state.value = state.value.copy(
                    from = event.from
                )
            }
            is SearchComfortnessEvent.ChangeToValue -> {
                _state.value = state.value.copy(
                    to = event.to
                )
            }
            SearchComfortnessEvent.StartSearch -> {
                // TODO: set value of mode to actual values from state, rn is ony state vars being updated 
            }
        }
    }

}