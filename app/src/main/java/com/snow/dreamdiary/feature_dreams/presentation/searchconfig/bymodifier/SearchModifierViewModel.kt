package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.common.util.extensions.splitTrimmed
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


@HiltViewModel
class SearchModifierViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchModifierState())
    val state: State<SearchModifierState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: SearchModifierEvent) {
        when (event) {
            is SearchModifierEvent.TextFieldValueChanged -> {
                _state.value = state.value.copy(
                    textFieldInput = event.value,
                    mode = state.value.mode.copy(
                        values = event.value.splitTrimmed().toMutableList()
                    )
                )
            }
            is SearchModifierEvent.ToggleDialogShow -> {
                _state.value = state.value.copy(
                    showDialog = event.show
                )
            }
            is SearchModifierEvent.SelectGate -> {
                _state.value = state.value.copy(
                    mode = state.value.mode.copy(
                        gate = event.gate
                    )
                )
            }
            SearchModifierEvent.StartSearch -> {

            }
        }
    }

    sealed class UIEvent{
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }
}