package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey;

import android.view.View
import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
public class DailySurveyViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(DailySurveyState())
    val state: State<DailySurveyState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: DailySurveyEvent){
        when (event) {
            is DailySurveyEvent.ChangeActivity -> {
                _state.value = state.value.copy(
                    surveyData = state.value.surveyData.copy(
                        physicalActivity = event.activity
                    )
                )
            }
            is DailySurveyEvent.ChangeComfortness -> {
                _state.value = state.value.copy(
                    surveyData = state.value.surveyData.copy(
                        comfortness = event.comfortness
                    )
                )
            }
            is DailySurveyEvent.ChangeCreatedat -> {
                TODO()
            }
            is DailySurveyEvent.ChangeDidDream -> {
                _state.value = state.value.copy(
                    surveyData = state.value.surveyData.copy(
                        didDream = event.didDream
                    )
                )
            }
            is DailySurveyEvent.ChangeHealth -> {
                _state.value = state.value.copy(
                    surveyData = state.value.surveyData.copy(
                        health = event.health
                    )
                )
            }
            is DailySurveyEvent.ChangeTimeSlept -> {
                _state.value = state.value.copy(
                    timeSlept = event.timeSlept
                )
            }
            is DailySurveyEvent.ChangeDreamsNum -> {
                _state.value = state.value.copy(
                    dreamsNum = event.dreamsNum
                )
            }
            DailySurveyEvent.Send -> {

            }
        }
    }


    sealed class UIEvent{
        data class Message(@StringRes val res: Int): UIEvent()
    }
}