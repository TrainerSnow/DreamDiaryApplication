package com.snow.dreamdiary.feature_dreams.presentation.search.selectmode;

import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dreams.domain.usecase.DreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectSearchModeViewModel @Inject constructor(
    private val dreamUseCases: DreamUseCases
): ViewModel() {

}