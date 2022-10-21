package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey;

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dailysurvey.domain.usecase.SurveyUseCases
import com.snow.dreamdiary.feature_dailysurvey.domain.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DailySurveyViewModel"

@HiltViewModel
public class DailySurveyViewModel @Inject constructor(
    private val surveyUseCases: SurveyUseCases
): ViewModel() {

    private val _state = mutableStateOf(DailySurveyState())
    val state: State<DailySurveyState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            val surveys = surveyUseCases.getSurveys().first()
            val thisDayBegin = TimeUtil.thisDayStartInMillis()

            if (!surveys.all {
                    it.createdAt != thisDayBegin
                }) {
                _state.value = state.value.copy(
                    canSubmitSurvey = false
                )
            }else{
                _state.value = state.value.copy(
                    canSubmitSurvey = true
                )
            }

        }
    }

    fun onEvent(event: DailySurveyEvent) {
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
                try {
                    val timesSlept = _state.value.timeSlept.toInt()
                    val dreamsNum = _state.value.timeSlept.toInt()

                    if(timesSlept > 24){
                        viewModelScope.launch {
                            _actionFlow.emit(UIEvent.Message(R.string.sleep_over_24))
                        }
                        return
                    }

                    _state.value = state.value.copy(
                        surveyData = state.value.surveyData.copy(
                            timeSlept = timesSlept.toLong(),
                            dreamsNum = if (
                                state.value.surveyData.didDream
                            )
                                dreamsNum
                            else
                                -1
                        )
                    )

                    viewModelScope.launch {
                        surveyUseCases.addSurvey(_state.value.surveyData)
                        val surveys = surveyUseCases.getSurveys().first()
                        _actionFlow.emit(UIEvent.Back)
                    }


                } catch (e: Exception) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.input_numbers_range_10))
                    }
                }
            }
        }
    }


    sealed class UIEvent{
        data class Message(@StringRes val res: Int): UIEvent()
        object Back : UIEvent()
    }
}