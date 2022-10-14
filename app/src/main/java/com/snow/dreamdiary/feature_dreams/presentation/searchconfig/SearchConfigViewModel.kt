package com.snow.dreamdiary.feature_dreams.presentation.searchconfig

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchConfigViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel()