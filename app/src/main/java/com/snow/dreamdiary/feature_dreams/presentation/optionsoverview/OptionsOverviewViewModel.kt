package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.DreamApp
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.usecase.SurveyUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.apache.commons.csv.CSVFormat
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.inject.Inject

private const val TAG = "OptionsOverviewViewMode"

@HiltViewModel
class OptionsOverviewViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases,
    private val surveyUseCases: SurveyUseCases
) : ViewModel() {

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: OptionsOverviewEvent) {
        when (event) {
            is OptionsOverviewEvent.GoToScreen -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.GoToScreen(event.screen))
                }
            }
            OptionsOverviewEvent.Backup -> {
                viewModelScope.launch {
                    val context = DreamApp.getAppContext()

                    /*
                    Getting the dreams/surveys as .csv format
                     */

                    val dreams = dreamUseCases.getDreams().first()
                    val surveys = surveyUseCases.getSurveys()

                    val dreamContent = StringBuilder()
                    val surveyContent = StringBuilder()

                    dreams.forEach { dream ->
                        dream.apply {
                            CSVFormat.DEFAULT.printRecord(
                                dreamContent,
                                description,
                                annotation,
                                persons.joinToString(),
                                feelings.joinToString(),
                                locations.joinToString(),
                                comfortness,
                                createdAt,
                                dreamtAt
                            )
                        }
                    }

                    surveys.forEach { survey ->
                        survey.apply {
                            CSVFormat.DEFAULT.printRecord(
                                surveyContent,
                                didDream,
                                dreamsNum,
                                timeSlept,
                                health,
                                physicalActivity,
                                comfortness,
                                createdAt
                            )
                        }
                    }

                    val contents = listOf(dreamContent, surveyContent)

                    /*
                    Zipping and saving them
                     */
                    //Zip file for the current backup
                    val zipFile = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path.plus(
                            "/"
                        )
                            .plus("dream_backup_${TimeFormatUtil.getMillisDayTimeFormatted(System.currentTimeMillis())}")
                            .plus(".zip")
                    )
                    val zipOut = ZipOutputStream(zipFile.outputStream())

                    contents.forEach {
                        val name = if(it == dreamContent) "dreams.csv" else "surveys.csv"
                        val data = it.toString().encodeToByteArray()

                        val entry = ZipEntry(name)
                        zipOut.putNextEntry(entry)
                        zipOut.write(data, 0, data.size)
                    }

                    zipOut.close()
                }
            }
            is OptionsOverviewEvent.RestoreBackup -> {

            }
        }
    }

    sealed class UIEvent {
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }

}