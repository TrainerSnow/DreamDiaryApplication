package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewDreamsViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ViewDreamsState())
    val state: State<ViewDreamsState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    private var refreshDreamsJob: Job? = null

    init {
        refreshDreams(_state.value.sortingOrder)
    }

    fun onEvent(event: ViewDreamsEvent) {
        when (event) {
            is ViewDreamsEvent.DeleteDream -> {
                viewModelScope.launch {
                    dreamUseCases.deleteDreams(event.dream)
                    refreshDreams(_state.value.sortingOrder)
                    // TODO: Keep reference to deleted dream, enable restoring deleted dreams
                }
            }
            is ViewDreamsEvent.EditDream -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.Edit(state.value.dreams[state.value.currentDreamIndex]))
                }
            }
            is ViewDreamsEvent.RenewOrder -> {
                refreshDreams(event.dreamOrder)
            }
            ViewDreamsEvent.ToggleOrderMenu -> {
                _state.value = state.value.copy(
                    isOrderMenuExpanded = !state.value.isOrderMenuExpanded
                )
            }
            ViewDreamsEvent.NextDream -> {
                val dreamsSize = _state.value.dreams.size
                val currentDreamIndex = _state.value.currentDreamIndex

                if (currentDreamIndex > dreamsSize - 1) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.reached_last_dream))
                    }
                } else {
                    val nextIndex = currentDreamIndex + 1

                    _state.value = state.value.copy(
                        currentDreamIndex = nextIndex
                    )
                }
            }
            ViewDreamsEvent.RecentDream -> {
                val currentDreamIndex = _state.value.currentDreamIndex

                if (currentDreamIndex == 0) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.reached_first_dream))
                    }
                } else {
                    val nextIndex = currentDreamIndex - 1

                    _state.value = state.value.copy(
                        currentDreamIndex = nextIndex
                    )
                }
            }

        }
    }


    private fun refreshDreams(order: DreamOrder) {
        refreshDreamsJob?.cancel()

        refreshDreamsJob = viewModelScope.launch {

            _state.value = state.value.copy(
                dreams = dreamUseCases.getDreams(dreamOrder = order)
                    .first(),
                sortingOrder = order
            )

        }
    }

    sealed class UIEvent {
        data class Message(@StringRes val message: Int) : UIEvent()
        data class Edit(val dream: Dream) : UIEvent()
    }

}