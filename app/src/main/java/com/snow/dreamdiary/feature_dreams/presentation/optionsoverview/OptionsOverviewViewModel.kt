package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.DreamApp
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.usecase.SurveyUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.csv.CSVFormat
import java.io.File
import java.io.StringReader
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
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
                                persons.joinToString(separator = ";"),
                                feelings.joinToString(separator = ";"),
                                locations.joinToString(separator = ";"),
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
                CoroutineScope(Dispatchers.IO).launch {
                    /*
                Getting zip file content
                 */

                    val context = DreamApp.getAppContext()

                    val uri = event.data
                    val zis = ZipInputStream(context.contentResolver.openInputStream(uri))

                    var entry = zis.nextEntry

                    var dreamContent: String? = null
                    var surveyContent: String? = null

                    while (entry != null) {
                        val readData = StringBuilder()

                        val buffer = ByteArray(4096)
                        var len = zis.read(buffer)
                        while (len > 0) {
                            readData.append(String(buffer))
                            len = zis.read(buffer)
                        }

                        if (entry.name == "dreams.csv")
                            dreamContent = readData.toString()
                        else if (entry.name == "surveys.csv")
                            surveyContent = readData.toString()

                        entry = zis.nextEntry
                    }

                    /*
                    Parsing file contents
                     */

                    val dreams = mutableListOf<Dream>()
                    val surveys = mutableListOf<DailySurveyData>()

                    val dreamParser = CSVFormat.DEFAULT.parse(StringReader(dreamContent))
                    val surveyParser = CSVFormat.DEFAULT.parse(StringReader(surveyContent))

                    dreamParser.forEach { record ->
                        val values = record.toList()
                        if(values.size < 8)
                            return@forEach

                        dreams.add(
                            Dream(
                                description = values[0],
                                annotation = values[1],
                                persons = values[2].split(";"),
                                feelings = values[3].split(";"),
                                locations = values[4].split(";"),
                                comfortness = values[5].toInt(),
                                dreamtAt = values[6].toLong(),
                                createdAt = values[7].toLong()
                            )
                        )

                    }

                    surveyParser.forEach { record ->
                        val values = record.toList()
                        if(values.size < 7)
                            return@forEach
                        surveys.add(
                            DailySurveyData(
                                didDream = values[0].toBoolean(),
                                dreamsNum = values[1].toInt(),
                                timeSlept = values[2].toInt(),
                                health = values[3].toInt(),
                                physicalActivity = values[4].toInt(),
                                comfortness = values[5].toInt(),
                                createdAt = values[6].toLong()
                            )
                        )
                    }

                    zis.closeEntry()
                    zis.close()

                    /*
                    Loading data into database
                     */

                    //Deleting old ones
                    dreamUseCases.deleteAllDreamsUseCase()
                    surveyUseCases.deleteAllSurveysUseCase()

                    //Adding new ones
                    dreams.forEach {
                        dreamUseCases.addDream(it)
                    }

                    surveys.forEach {
                        surveyUseCases.addSurvey(it)
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }

}