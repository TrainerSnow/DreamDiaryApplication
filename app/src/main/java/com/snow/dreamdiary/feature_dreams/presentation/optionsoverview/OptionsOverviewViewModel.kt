package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.DreamApp
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamSurveyUseCases
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.usecase.SurveyUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.apache.commons.csv.CSVFormat
import java.io.File
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
                    val dreams = dreamUseCases.getDreams().first()
                    val surveys = surveyUseCases.getSurveys()

                    val dreamContent = StringBuilder()
                    val surveyContent = StringBuilder()

                    /*
                    Get Data as CSV Format
                     */
                    dreams.forEach {
                        CSVFormat.DEFAULT.printRecord(
                            dreamContent,
                            it.description,
                            it.annotation,
                            it.persons.joinToString(),
                            it.feelings.joinToString(),
                            it.locations.joinToString(),
                            it.comfortness,
                            it.createdAt,
                            it.dreamtAt
                        )
                    }

                    surveys.forEach {
                        CSVFormat.DEFAULT.printRecord(
                            surveyContent,
                            it.didDream,
                            it.dreamsNum,
                            it.timeSlept,
                            it.health,
                            it.physicalActivity,
                            it.comfortness,
                            it.createdAt
                        )
                    }

                    /*
                    Cache Data
                     */

                    val chacheDir = DreamApp.getAppContext().cacheDir

                    val dreamCacheFile = File(chacheDir.path.plus("/dreams_cached.csv"))
                    val surveyCacheFile = File(chacheDir.path.plus("/surveys_cached.csv"))

                    dreamCacheFile.apply {
                        val fos = outputStream()
                        fos.write(dreamContent.toString().encodeToByteArray())
                        fos.close()
                    }

                    surveyCacheFile.apply {
                        val fos = outputStream()
                        fos.write(surveyContent.toString().encodeToByteArray())
                        fos.close()
                    }

                    /*
                    Zip it
                     */
                    val zipFile = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path.plus("/dreams_backup.zip")
                    )

                    val filesToZip = listOf(dreamCacheFile, surveyCacheFile)

                    val fos = zipFile.outputStream()
                    val zipout = ZipOutputStream(fos)

                    filesToZip.forEach {
                        val fis = it.inputStream()

                        val entry = ZipEntry(it.name)

                        zipout.putNextEntry(entry)

                        val buffer = ByteArray(1024)
                        var len: Int = fis.read(buffer)
                        while(len > 0){
                            zipout.write(buffer)
                            len = fis.read(buffer)
                        }

                        fis.close()
                        zipout.closeEntry()
                    }

                    zipout.close()
                    fos.close()

                    /*
                    Delete Cache Files
                     */
                    filesToZip.forEach { it.delete() }

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