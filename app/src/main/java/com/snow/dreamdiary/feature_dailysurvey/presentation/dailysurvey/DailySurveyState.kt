package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey

import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dailysurvey.domain.util.TimeUtil

data class DailySurveyState(
    val surveyData: DailySurveyData = DailySurveyData(
        createdAt = TimeUtil.thisDayStartInMillis()
    ),
    val dreamsNum: String = "0",
    val timeSlept: String = 8.toString(),

    val canSubmitSurvey: Boolean = true
)
