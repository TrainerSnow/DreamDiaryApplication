package com.snow.dreamdiary.feature_dreams.presentation.startscreen;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class StartScreenViewModel @Inject constructor(

): ViewModel() {


    private val _actionFlow = MutableSharedFlow<UIEvent>()
    val actionFlow = _actionFlow.asSharedFlow()

    fun onEvent(event: StartScreenEvent){
        when (event) {
            is StartScreenEvent.OpenScreen -> {
                viewModelScope.launch {
                    _actionFlow.emit(UIEvent.OpenScreen(event.screen))
                }
            }
        }
    }

    sealed class UIEvent{
        data class OpenScreen(val screen: DreamScreens): UIEvent()
    }
}