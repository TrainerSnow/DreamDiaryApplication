package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.DreamApp
import com.snow.dreamdiary.feature_dreams.data.source.DailySurveyDataDatabase
import com.snow.dreamdiary.feature_dreams.data.source.DreamDatabase
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.inject.Inject

private const val TAG = "OptionsOverviewViewMode"

@HiltViewModel
class OptionsOverviewViewModel @Inject constructor(

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
                Log.e(TAG, "onEvent: in Backup call")
                viewModelScope.launch {
                    Log.e(TAG, "onEvent: Started coroutine")
                    val context = DreamApp.getAppContext()

                    val dreamsDbFile = context.getDatabasePath(DreamDatabase.DATABASE_NAME)
                    val surveysDbFile =
                        context.getDatabasePath(DailySurveyDataDatabase.DATABASE_NAME)

                    val filesToBackup = listOf(
                        File(dreamsDbFile.path),
                        File(dreamsDbFile.path + "-shm"),
                        File(dreamsDbFile.path + "-wal"),
                        File(surveysDbFile.path),
                        File(surveysDbFile.path + "-shm"),
                        File(surveysDbFile.path + "-wal"),
                    )


                    val downloadsDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val zipfile = File(downloadsDir.path + "/dream_backup.zip")


                    val fos =
                        FileOutputStream(zipfile) //Write the File where the zip file will be stored
                    val zos = ZipOutputStream(fos) //Used to configure the zip file


                    try {
                        filesToBackup.forEach {
                            val entry = ZipEntry(it.name)
                            zos.putNextEntry(entry)

                            val fileData = ByteArray(it.inputStream().available())
                            it.inputStream().read(fileData)
                            zos.write(fileData)
                            zos.closeEntry()
                        }
                    } catch (e: Exception) {
                        TODO()
                    } finally {
                        zos.close()
                        fos.close()
                    }
                }
            }
            OptionsOverviewEvent.RestoreBackup -> {

            }
        }
    }

    sealed class UIEvent {
        data class GoToScreen(val screen: DreamScreens) : UIEvent()
    }

}