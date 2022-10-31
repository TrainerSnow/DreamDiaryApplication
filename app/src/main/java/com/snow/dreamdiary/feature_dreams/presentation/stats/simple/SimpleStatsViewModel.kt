package com.snow.dreamdiary.feature_dreams.presentation.stats.simple;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.common.util.Arrayutil
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class SimpleStatsViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
): ViewModel() {

    private val _state = mutableStateOf(SimpleStatsState())
    val state: State<SimpleStatsState> = _state

    init {
        viewModelScope.launch {
            val modifierPair = dreamUseCases.getModifiersInTime(
                state.value.fromDateValue,
                state.value.toDateValue,
                state.value.modifier
            )
            val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(modifierPair.first, modifierPair.second, true)
            _state.value = state.value.copy(
                modifierLabels = sortedPairOfArrays.first,
                modifierData = sortedPairOfArrays.second
            )
        }
    }

    fun onEvent(event: SimpleStatsEvent) {
        when (event) {
            is SimpleStatsEvent.ChangeModifier -> {
                _state.value = state.value.copy(
                    modifier = event.modifier
                )
                viewModelScope.launch {
                    val modifierPair = dreamUseCases.getModifiersInTime(
                        state.value.fromDateValue,
                        state.value.toDateValue,
                        state.value.modifier
                    )
                    val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(modifierPair.first, modifierPair.second, true)
                    _state.value = state.value.copy(
                        modifierLabels = sortedPairOfArrays.first,
                        modifierData = sortedPairOfArrays.second
                    )
                }
            }
            SimpleStatsEvent.ToggleDialog -> {
                _state.value = state.value.copy(
                    showDialog = !state.value.showDialog
                )
            }
            is SimpleStatsEvent.ChangeTimeFrom -> {
                _state.value = state.value.copy(
                    fromDateValue = event.newTime,
                    fromDateVis = TimeFormatUtil.getMillisFormatted(event.newTime)
                )
                viewModelScope.launch {
                    val modifierPair = dreamUseCases.getModifiersInTime(
                        state.value.fromDateValue,
                        state.value.toDateValue,
                        state.value.modifier
                    )
                    val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(modifierPair.first, modifierPair.second, true)
                    _state.value = state.value.copy(
                        modifierLabels = sortedPairOfArrays.first,
                        modifierData = sortedPairOfArrays.second
                    )
                }
            }
            is SimpleStatsEvent.ChangeTimeTo -> {
                _state.value = state.value.copy(
                    toDateValue = event.newTime,
                    toDateVis = TimeFormatUtil.getMillisFormatted(event.newTime)
                )
                viewModelScope.launch {
                    val modifierPair = dreamUseCases.getModifiersInTime(
                        state.value.fromDateValue,
                        state.value.toDateValue,
                        state.value.modifier
                    )
                    val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(modifierPair.first, modifierPair.second,  true)
                    _state.value = state.value.copy(
                        modifierLabels = sortedPairOfArrays.first,
                        modifierData = sortedPairOfArrays.second
                    )
                }
            }
        }
    }

}