package com.snow.dreamdiary.feature_dreams.presentation.behaviouranalysis;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamSurveyUseCases
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class DreamBehaviourAnalysisViewModel @Inject constructor(
    private val dreamSurveyUseCases: DreamSurveyUseCases
): ViewModel() {

    private val _state = mutableStateOf(DreamBehaviourAnalysisState())
    val state: State<DreamBehaviourAnalysisState> = _state


    init{
        reloadValues()
    }

    fun onEvent(event: DreamBehaviourAnalysisEvent) {
        when (event) {
            DreamBehaviourAnalysisEvent.ToggleAffectable -> {
                _state.value = state.value.copy(
                    affectable = if (state.value.affectable is DreamAffectables.Occurence)
                        DreamAffectables.Comfortness
                    else
                        DreamAffectables.Occurence
                )
                reloadValues()
            }
        }
    }

    private fun reloadValues(){
        viewModelScope.launch {
            val pcTimeSlept = try {
                dreamSurveyUseCases.getPercentChangeAffectableBySurveyDataPart(
                    {
                        it.timeSlept
                    },
                    state.value.affectable
                )
            }catch (_: Exception){
                null
            }
            val pcHealth = try {
                dreamSurveyUseCases.getPercentChangeAffectableBySurveyDataPart(
                    {
                        it.health
                    },
                    state.value.affectable
                )
            }catch (_ : Exception){
                null
            }
            val pcPhysAct = try {
                dreamSurveyUseCases.getPercentChangeAffectableBySurveyDataPart(
                    {
                        it.physicalActivity
                    },
                    state.value.affectable
                )
            }catch(_: Exception){
                null
            }

            val pcDailyComf = try {
                dreamSurveyUseCases.getPercentChangeAffectableBySurveyDataPart(
                    {
                        it.comfortness
                    },
                    state.value.affectable
                )
            }catch (_: Exception){
                null
            }

            _state.value = state.value.copy(
                percentValues = listOf(
                    pcTimeSlept,
                    pcPhysAct,
                    pcHealth,
                    pcDailyComf
                )
            )
        }
    }
}