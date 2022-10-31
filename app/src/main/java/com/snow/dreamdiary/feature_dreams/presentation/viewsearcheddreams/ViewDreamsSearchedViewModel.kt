package com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes
import com.snow.dreamdiary.feature_dreams.presentation.navigation.KEY_MODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class ViewDreamsSearchedViewModel @Inject constructor(
    val dreamUseCases: DreamUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(
        ViewDreamsSearchedState(
            searchMode = DreamSearchModes.fromJson(
                JSONObject(
                    savedStateHandle.get<String>(KEY_MODE) ?: throw java.lang.IllegalStateException(
                        "No argument for key $KEY_MODE supplied!"
                    )
                )
            )
        )
    )
    val state: State<ViewDreamsSearchedState> = _state

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            val dreams: List<Dream>
            val timeNeeded = measureTimeMillis {
                dreams = dreamUseCases.getDreamsForSearchConfig(_state.value.searchMode)
            }
            _state.value = state.value.copy(
                dreams = dreams.toMutableList(),
                timeNeeded = timeNeeded
            )
        }
    }


    fun onEvent(event: ViewDreamsSearchedEvent) {
        when (event) {
            else -> {}
        }
    }

    sealed class UIEvent
}