package com.snow.dreamdiary.feature_dreams.presentation.addeditdream;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditDreamViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
) : ViewModel() {

    private val _dreamDesc = mutableStateOf("")
    val dreamDesc: State<String> = _dreamDesc

    private val _dreamAnnotation = mutableStateOf("")
    val dreamAnnotation: State<String> = _dreamAnnotation

    private val _persons = mutableStateOf(emptyList<String>())
    val persons: State<List<String>> = _persons

    private val _feelings = mutableStateOf(emptyList<String>())
    val feelings: State<List<String>> = _feelings

    private val _locations = mutableStateOf(emptyList<String>())
    val locations: State<List<String>> = _locations

    private val _comfortness = mutableStateOf(emptyList<String>())
    val comfortness: State<List<String>> = _comfortness

    private val _dreamtAt = mutableStateOf(emptyList<String>())
    val dreamtAt: State<List<String>> = _dreamtAt

    private val _dreamColor = mutableStateOf(Dream.dreamColors.random())
    val dreamColor = _dreamColor



}