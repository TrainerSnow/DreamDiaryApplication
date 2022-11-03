package com.snow.dreamdiary.feature_dreams.presentation.viewdream;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavScreens
import com.snow.dreamdiary.feature_dreams.presentation.navigation.KEY_DREAM_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class ViewDreamViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(ViewDreamState())
    val state: State<ViewDreamState> = _state

    private val _dreamId = mutableStateOf<Int?>(savedStateHandle[KEY_DREAM_ID])
    val dreamId: State<Int?> = _dreamId

    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    var recentlyDeletedDream: Dream? = null

    init {
        if(dreamId.value != null){
            viewModelScope.launch {
                val dream = dreamUseCases.getDreamById(dreamId.value)
                _state.value = state.value.copy(
                    dream = dream
                )
            }
        }
    }

    fun onEvent(event: ViewDreamEvent){
        when (event) {
            ViewDreamEvent.ToggleAnn -> {
                _state.value = state.value.copy(
                    annotationExpanded = !state.value.annotationExpanded
                )
            }
            ViewDreamEvent.ToggleComf -> {
                _state.value = state.value.copy(
                    comfortnessExpanded = !state.value.comfortnessExpanded
                )
            }
            ViewDreamEvent.ToggleDesc -> {
                _state.value = state.value.copy(
                    descExpanded = !state.value.descExpanded
                )
            }
            ViewDreamEvent.ToggleFeeling -> {
                _state.value = state.value.copy(
                    feelingsExpanded = !state.value.feelingsExpanded
                )
            }
            ViewDreamEvent.ToggleLocation -> {
                _state.value = state.value.copy(
                    locationsExpanded = !state.value.locationsExpanded
                )
            }
            ViewDreamEvent.TogglePerson -> {
                _state.value = state.value.copy(
                    personsExpanded = !state.value.personsExpanded
                )
            }
            ViewDreamEvent.Delete -> {
                recentlyDeletedDream = state.value.dream
                if(state.value.dream != null){
                    viewModelScope.launch {
                        dreamUseCases.deleteDreams(state.value.dream!!)
                    }
                }
            }
            ViewDreamEvent.Edit -> {
                if(state.value.dream != null){
                    viewModelScope.launch {
                        _actionFlow.emit(UIEvent.GoToScreen(
                            BottomNavScreens.AddEditDreamScreen.passDreamId(state.value.dream!!.id!!)
                        ))
                    }
                }
            }
            ViewDreamEvent.Restore -> {
                if(recentlyDeletedDream != null){
                    viewModelScope.launch {
                        dreamUseCases.addDream(recentlyDeletedDream!!)
                        recentlyDeletedDream = null
                    }
                }
            }
        }
    }

    sealed class UIEvent{
        data class GoToScreen(val route: String): UIEvent()
    }

}