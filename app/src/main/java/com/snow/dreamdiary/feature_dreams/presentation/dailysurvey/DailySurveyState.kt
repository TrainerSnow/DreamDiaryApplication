package com.snow.dreamdiary.feature_dreams.presentation.dailysurvey

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.util.TimeUtil

data class DailySurveyState(
    val surveyData: DailySurveyData = DailySurveyData(
        createdAt = TimeUtil.thisDayStartInMillis()
    ),
    val dreamsNum: String = "0",
    val timeSlept: String = 8.toString(),

    val canSubmitSurvey: Boolean? = null
)
