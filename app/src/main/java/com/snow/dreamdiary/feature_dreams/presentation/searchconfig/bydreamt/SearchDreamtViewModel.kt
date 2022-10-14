package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt;

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class SearchDreamtViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
): ViewModel(){

}