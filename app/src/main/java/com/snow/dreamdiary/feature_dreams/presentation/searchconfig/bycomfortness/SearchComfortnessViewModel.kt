package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchComfortnessViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchComfortnessState())
    val state: State<SearchComfortnessState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: SearchComfortnessEvent) {
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
                try {
                    val from = state.value.from.toByte()
                    val to = state.value.to.toByte()

                    if (
                        listOf(from, to).any {
                            it < 0 || it > 10
                        }
                    ) {
                        throw NumberFormatException()
                    }else if(from >= to){
                        viewModelScope.launch {
                            _actionFlow.emit(UIEvent.Message(R.string.from_larger_to))
                        }
                        return
                    }

                    _state.value = state.value.copy(
                        mode = state.value.mode.copy(
                            fromVal = from,
                            toVal = to
                        )
                    )

                } catch (e: NumberFormatException) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.input_numbers_range_10))
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class Message(@StringRes val message: Int) : UIEvent()
    }

}