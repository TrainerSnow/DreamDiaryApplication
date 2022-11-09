package com.snow.dreamdiary.feature_dreams.presentation.behaviouranalysis;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class DreamBehaviourAnalysisViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(DreamBehaviourAnalysisState())
    val state: State<DreamBehaviourAnalysisState> = _state

    fun onEvent(event: DreamBehaviourAnalysisEvent) {
        when (event) {
            DreamBehaviourAnalysisEvent.ToggleAffectable -> {
                _state.value = state.value.copy(
                    affectable = if (state.value.affectable is DreamAffectables.Occurence)
                        DreamAffectables.Comfortness
                    else
                        DreamAffectables.Occurence
                )
            }
        }
    }
}