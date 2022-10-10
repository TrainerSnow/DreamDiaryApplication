package com.snow.dreamdiary.feature_dreams.presentation.addeditdream;

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.common.exception.MessageException
import com.snow.dreamdiary.common.util.IntState
import com.snow.dreamdiary.common.util.LongState
import com.snow.dreamdiary.common.util.StringState
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDreamViewModel @Inject constructor(
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

    private val _dreamColor = mutableStateOf(Dream.dreamColors.random())
    val dreamColor: State<Color> = _dreamColor

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    //To be called from the Screen class
    fun onEvent(event: AddEditDreamEvent) {
        when (event) {
            is AddEditDreamEvent.Add -> {
                viewModelScope.launch {
                    val now = System.currentTimeMillis()
                    try {
                        dreamUseCases.addDream(
                            Dream(
                                description = dreamDesc.value.text,
                                annotation = dreamAnnotation.value.text,
                                persons = persons.value.text.split(";"),
                                feelings = feelings.value.text.split(";"),
                                locations = locations.value.text.split(";"),
                                comfortness = comfortness.value.value,
                                createdAt = now,
                                dreamtAt = if (dreamtAt.value.value == 0L)
                                    now
                                else
                                    dreamtAt.value.value,
                                color = dreamColor.value
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
            is AddEditDreamEvent.ChangeDreamColor -> {
                _dreamColor.value = dreamColor.value.copy(
                    alpha = event.value.alpha,
                    red = event.value.red,
                    green = event.value.green,
                    blue = event.value.blue,
                )
            }
            AddEditDreamEvent.Cancel -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.GoBack)
                }
            }
        }
    }

    sealed class UIEvent {
        data class Message(@StringRes val res: Int) : UIEvent()
        object GoBack : UIEvent()
    }
}