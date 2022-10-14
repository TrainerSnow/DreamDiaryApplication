package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.common.exception.MessageException
import com.snow.dreamdiary.common.util.BooleanState
import com.snow.dreamdiary.common.util.IntState
import com.snow.dreamdiary.common.util.LongState
import com.snow.dreamdiary.common.util.StringState
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.KEY_DREAM_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDreamViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dreamUseCases: DreamUseCases
) : ViewModel() {

    private val _dreamDesc = mutableStateOf(StringState())
    val dreamDesc: State<StringState> = _dreamDesc

    private val _dreamAnnotation = mutableStateOf(StringState())
    val dreamAnnotation: State<StringState> = _dreamAnnotation

    private val _persons = mutableStateOf(StringState())
    val persons: State<StringState> = _persons

    private val _feelings = mutableStateOf(StringState())
    val feelings: State<StringState> = _feelings

    private val _locations = mutableStateOf(StringState())
    val locations: State<StringState> = _locations

    private val _comfortness = mutableStateOf(IntState())
    val comfortness: State<IntState> = _comfortness

    private val _dreamtAt = mutableStateOf(LongState())
    val dreamtAt: State<LongState> = _dreamtAt

    private val _createdAt = mutableStateOf(LongState())
    val createdAt: State<LongState> = _createdAt

    private val _newPersons = mutableStateOf(listOf<String>())
    val newPersons: State<List<String>> = _newPersons

    private val _newFeelings = mutableStateOf(listOf<String>())
    val newFeelings: State<List<String>> = _newFeelings

    private val _newLocations = mutableStateOf(listOf<String>())
    val newLocations: State<List<String>> = _newLocations

    private val _dreamId = mutableStateOf<Int?>(savedStateHandle[KEY_DREAM_ID])
    val dreamId: State<Int?> = _dreamId

    init {
        if (_dreamId.value != null) {
            val id = _dreamId.value as Int
            if (id != -1) {
                viewModelScope.launch {
                    val toEditDream = dreamUseCases.getDreamById(id)
                    if (toEditDream == null) {
                        this.cancel()
                        return@launch
                    }

                    _dreamDesc.value.text = toEditDream.description
                    _dreamAnnotation.value.text = toEditDream.annotation
                    _persons.value.text = toEditDream.persons.joinToString(";")
                    _feelings.value.text = toEditDream.feelings.joinToString(";")
                    _locations.value.text = toEditDream.locations.joinToString(";")
                    _dreamtAt.value.value = toEditDream.dreamtAt
                    _createdAt.value.value = toEditDream.createdAt

                }
            }
        }
    }


    private val _shouldShowDialog = mutableStateOf(
        BooleanState(
            value = false
        )
    )
    val shouldShowDialog: State<BooleanState> = _shouldShowDialog


    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    //To be called from the Screen class
    fun onEvent(event: AddEditDreamEvent) {
        when (event) {
            is AddEditDreamEvent.DismissAddRequest -> {
                _shouldShowDialog.value = shouldShowDialog.value.copy(
                    value = false
                )
            }
            is AddEditDreamEvent.RequestAdd -> {

                if (_dreamId.value != null) {
                    val id = _dreamId.value as Int
                    if (id != -1) {
                        viewModelScope.launch {
                            val dreamToEdit = dreamUseCases.getDreamById(id)
                            if (dreamToEdit == null) {
                                this.cancel()
                                return@launch
                            }
                            dreamUseCases.deleteDreams(dreamToEdit)
                        }
                    }
                }

                val persons = getValuesFromString(persons.value.text)
                val feelings = getValuesFromString(feelings.value.text)
                val locations = getValuesFromString(locations.value.text)

                _newPersons.value = dreamUseCases.getNewPersons(persons)
                _newFeelings.value = dreamUseCases.getNewFeelings(feelings)
                _newLocations.value = dreamUseCases.getNewLocations(locations)

                if (
                    listOf(_newPersons.value, _newFeelings.value, _newLocations.value).any {
                        it.isNotEmpty()
                    }
                ) {
                    _shouldShowDialog.value = shouldShowDialog.value.copy(
                        value = true
                    )
                } else {
                    onEvent(AddEditDreamEvent.Add)
                }
            }
            is AddEditDreamEvent.Add -> {
                viewModelScope.launch {
                    val now = System.currentTimeMillis()
                    try {
                        dreamUseCases.addDream(
                            Dream(
                                description = dreamDesc.value.text,
                                annotation = dreamAnnotation.value.text,
                                persons = getValuesFromString(persons.value.text),
                                feelings = getValuesFromString(feelings.value.text),
                                locations = getValuesFromString(locations.value.text),
                                comfortness = comfortness.value.value,
                                createdAt = if (dreamtAt.value.value == 0L)
                                    now
                                else
                                    dreamtAt.value.value,
                                dreamtAt = if (dreamtAt.value.value == 0L)
                                    now
                                else
                                    dreamtAt.value.value,
                            )
                        )
                        _actionFlow.emit(UIEvent.GoBack)
                    } catch (e: MessageException) {
                        _actionFlow.emit(UIEvent.Message(e.text))
                    }
                }
            }
            is AddEditDreamEvent.ChangeAnnotation -> {
                _dreamAnnotation.value = dreamAnnotation.value.copy(
                    text = event.value
                )
            }
            is AddEditDreamEvent.ChangeComfortness -> {
                _comfortness.value = comfortness.value.copy(
                    value = event.value
                )
            }
            is AddEditDreamEvent.ChangeDescription -> {
                _dreamDesc.value = dreamDesc.value.copy(
                    text = event.value
                )
            }
            is AddEditDreamEvent.ChangeDreamtAt -> {
                _dreamtAt.value = dreamtAt.value.copy(
                    value = event.value
                )
            }
            is AddEditDreamEvent.ChangeFeelings -> {
                _feelings.value = feelings.value.copy(
                    text = event.value
                )
            }
            is AddEditDreamEvent.ChangeLocations -> {
                _locations.value = locations.value.copy(
                    text = event.value
                )
            }
            is AddEditDreamEvent.ChangePersons -> {
                _persons.value = persons.value.copy(
                    text = event.value
                )
            }
            AddEditDreamEvent.Cancel -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.GoBack)
                }
            }
        }
    }

    private fun getValuesFromString(s: String): List<String> {
        return s.split(";").map {
            it.trim()
        }
    }

    sealed class UIEvent {
        data class Message(@StringRes val res: Int) : UIEvent()
        object GoBack : UIEvent()
    }
}