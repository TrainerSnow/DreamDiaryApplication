package com.snow.dreamdiary.feature_dreams.presentation.dailysurvey

sealed class DailySurveyEvent {
    data class ChangeDidDream(val didDream: Boolean) : DailySurveyEvent()
    data class ChangeDreamsNum(val dreamsNum: String) : DailySurveyEvent()
    data class ChangeTimeSlept(val timeSlept: String) : DailySurveyEvent()
    data class ChangeHealth(val health: Int) : DailySurveyEvent()
    data class ChangeActivity(val activity: Int) : DailySurveyEvent()
    data class ChangeComfortness(val comfortness: Int) : DailySurveyEvent()
    data class ChangeCreatedat(val createdat: Int) : DailySurveyEvent()

    object Send : DailySurveyEvent()
}
