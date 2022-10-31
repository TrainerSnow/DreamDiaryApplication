package com.snow.dreamdiary.feature_dreams.presentation.stats.advanced;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.common.util.Arrayutil
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class AdvancedStatsViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
): ViewModel() {

    private val _state = mutableStateOf(AdvancedStatsState())
    val state: State<AdvancedStatsState> = _state

    fun onEvent(event: AdvancedStatsEvent) {
        when (event) {
            is AdvancedStatsEvent.ChangeModifier -> {
                _state.value = state.value.copy(
                    modifier = event.modifier,
                    showComfortness = false
                )
                viewModelScope.launch {
                    val modifierMap = when (event.modifier) {
                        DreamModifier.Person -> {
                            dreamUseCases.getPersons(state.value.searchedValue)
                        }
                        DreamModifier.Feeling -> {
                            dreamUseCases.getFeelings(state.value.searchedValue)
                        }
                        DreamModifier.Location -> {
                            dreamUseCases.getLocations(state.value.searchedValue)
                        }
                    }
                    val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(
                        modifierMap.keys.toList(),
                        modifierMap.values.toList(),
                        true
                    )
                    _state.value = state.value.copy(
                        modifierLabels = sortedPairOfArrays.first,
                        modifierData = sortedPairOfArrays.second
                    )
                }
            }
            AdvancedStatsEvent.ChangeToComfortness -> {
                _state.value = state.value.copy(
                    modifier = null,
                    showComfortness = true
                )
                viewModelScope.launch {
                    val modifierMap = dreamUseCases.getComfortnesses(state.value.searchedValue)
                    val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(
                        modifierMap.keys.toList(),
                        modifierMap.values.toList(),
                        true
                    )
                    _state.value = state.value.copy(
                        modifierLabels = sortedPairOfArrays.first,
                        modifierData = sortedPairOfArrays.second
                    )
                }
            }
            is AdvancedStatsEvent.UpdateSearchedValue -> {
                _state.value = state.value.copy(
                    searchedValue = event.value
                )
            }
            AdvancedStatsEvent.Search -> {
                if(state.value.showComfortness){
                    viewModelScope.launch {
                        val modifierMap = dreamUseCases.getComfortnesses(state.value.searchedValue)
                        val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(
                            modifierMap.keys.toList(),
                            modifierMap.values.toList(),
                            true
                        )
                        _state.value = state.value.copy(
                            modifierLabels = sortedPairOfArrays.first,
                            modifierData = sortedPairOfArrays.second
                        )
                    }
                }else{
                    viewModelScope.launch {
                        val modifierMap = when (state.value.modifier) {
                            DreamModifier.Person -> {
                                dreamUseCases.getPersons(state.value.searchedValue)
                            }
                            DreamModifier.Feeling -> {
                                dreamUseCases.getFeelings(state.value.searchedValue)
                            }
                            DreamModifier.Location -> {
                                dreamUseCases.getLocations(state.value.searchedValue)
                            }
                            else -> {
                                throw IllegalStateException("IDK")
                            }
                        }
                        val sortedPairOfArrays = Arrayutil.sortArraysDependingOnFirst(
                            modifierMap.keys.toList(),
                            modifierMap.values.toList(),
                            true
                        )
                        _state.value = state.value.copy(
                            modifierLabels = sortedPairOfArrays.first,
                            modifierData = sortedPairOfArrays.second
                        )
                    }
                }
            }
        }
    }

}