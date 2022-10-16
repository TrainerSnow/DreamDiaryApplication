package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey;

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class DailySurveyViewModel @Inject constructor(
    val surveyUseCases: SurveyUseCases
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
                try {
                    val dreamsNum = _state.value.dreamsNum.toInt()
                    val timeSlept = _state.value.timeSlept.toInt()

                    if (timeSlept > 24) {
                        viewModelScope.launch {
                            _actionFlow.emit(UIEvent.Message(R.string.no_sleep_over_24))
                        }
                        return
                    }

                    _state.value = state.value.copy(
                        surveyData = state.value.surveyData.copy(
                            dreamsNum = dreamsNum,
                            timeSlept = timeSlept,
                            createdAt = TimeUtil.thisDayStartInMillis()
                        )
                    )

                    viewModelScope.launch {
                        surveyUseCases.addSurvey(_state.value.surveyData)
                    }

                } catch (_: Exception) {
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.Message(R.string.input_numbers_range_10))
                    }
                }
            }
        }
    }


    sealed class UIEvent{
        data class Message(@StringRes val message: Int) : UIEvent()
    }
}