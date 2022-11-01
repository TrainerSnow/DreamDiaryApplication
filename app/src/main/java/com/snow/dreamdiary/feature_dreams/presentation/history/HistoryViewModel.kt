package com.snow.dreamdiary.feature_dreams.presentation.history;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class HistoryViewModel @Inject constructor(
    val dreamUseCases: DreamUseCases
): ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                earliestTimeStamp = dreamUseCases.getEarliestDreamTimeStamp()
            )
            updategraph()
        }
    }

    fun onEvent(event: HistoryEvent){
        when (event) {
            HistoryEvent.EnableDreamView -> {
                _state.value = state.value.copy(
                    dreamView = !state.value.dreamView,
                    modifierView = !state.value.modifierView
                )
                updategraph()
            }
            HistoryEvent.EnableModifierView -> {
                _state.value = state.value.copy(
                    dreamView = !state.value.dreamView,
                    modifierView = !state.value.modifierView
                )
                updategraph()
            }
            is HistoryEvent.SearchedValueChanged -> {
                _state.value = state.value.copy(
                    searchedValue = event.value
                )
            }
            HistoryEvent.SendSearch -> {
                updategraph()
            }
            HistoryEvent.ToggleView -> {
                _state.value = state.value.copy(
                    actualView = !state.value.actualView,
                    changedView = !state.value.changedView
                )
                updategraph()
            }

        }
    }

    private fun updategraph(){
        viewModelScope.launch {
            if(state.value.modifierView){
                _state.value = state.value.copy(
                    timeStamps = dreamUseCases.getModifierTimeStamps(state.value.searchedValue)
                )
            }else if(state.value.dreamView){
                _state.value = state.value.copy(
                    timeStamps = dreamUseCases.getDreamTimeStamps()
                )
            }
        }
    }

}