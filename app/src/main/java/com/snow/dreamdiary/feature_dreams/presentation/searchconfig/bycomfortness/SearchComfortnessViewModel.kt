package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchComfortnessViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel()