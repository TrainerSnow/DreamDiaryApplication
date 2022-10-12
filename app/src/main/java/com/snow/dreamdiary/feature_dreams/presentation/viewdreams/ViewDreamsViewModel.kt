package com.snow.dreamdiary.feature_dreams.presentation.viewdreams;

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewDreamsViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ViewDreamsState())
    val state: State<ViewDreamsState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    var refreshDreamsJob: Job? = null

    init {
        refreshDreams(_state.value.sortingOrder)
    }

    fun onEvent(event: ViewDreamsEvent) {
        when (event) {
            is ViewDreamsEvent.DeleteDream -> {
                viewModelScope.launch {
                    val dream = dreamUseCases.getDreamById(event.dreamId) ?: return@launch
                    dreamUseCases.deleteDreams(dream)
                    // TODO: Keep reference to deleted dream, enable restoring deleted dreams
                }
            }
            is ViewDreamsEvent.EditDream -> {
                // TODO: Implement this: send user to AddEditDream with ID as argument
            }
            is ViewDreamsEvent.RenewOrder -> {
                refreshDreams(event.dreamOrder)
            }
            ViewDreamsEvent.CloseOrderMenu -> {
                _state.value = state.value.copy(
                    isOrderMenuExpanded = false
                )
            }
            ViewDreamsEvent.NextDream -> {
                val dreamsSize = _state.value.dreams.size
                val currentDreamIndex = _state.value.currentDreamIndex

                if (currentDreamIndex == dreamsSize - 1) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.reached_last_dream))
                    }
                    return
                }

                val nextIndex = currentDreamIndex + 1
                val nextDream = _state.value.dreams[nextIndex]

                _state.value = state.value.copy(
                    currentDream = nextDream,
                    currentDreamIndex = nextIndex
                )
            }
            ViewDreamsEvent.OpenOrderMenu -> {
                _state.value = state.value.copy(
                    isOrderMenuExpanded = true
                )
            }
            ViewDreamsEvent.RecentDream -> {
                val dreamsSize = _state.value.dreams.size
                val currentDreamIndex = _state.value.currentDreamIndex

                if (currentDreamIndex == 0) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.reached_first_dream))
                    }
                    return
                }

                val nextIndex = currentDreamIndex + 1
                val nextDream = _state.value.dreams[nextIndex]

                _state.value = state.value.copy(
                    currentDream = nextDream,
                    currentDreamIndex = nextIndex
                )
            }

        }
    }


    private fun refreshDreams(order: DreamOrder) {
        refreshDreamsJob?.cancel()

        refreshDreamsJob = viewModelScope.launch {
            dreamUseCases.getDreams(dreamOrder = order)
                .onEach {
                    _state.value = state.value.copy(
                        dreams = it,
                        sortingOrder = order
                    )
                }
        }
    }

    sealed class UIEvent {
        data class Message(@StringRes val message: Int) : UIEvent()
    }

}