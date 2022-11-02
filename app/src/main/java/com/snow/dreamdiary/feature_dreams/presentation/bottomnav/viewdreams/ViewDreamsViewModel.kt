package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            is ViewDreamsEvent.RenewOrder -> {
                refreshDreams(event.dreamOrder)
            }
            ViewDreamsEvent.ToggleOrderMenu -> {
                _state.value = state.value.copy(
                    isOrderMenuExpanded = !state.value.isOrderMenuExpanded
                )
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

    }

}