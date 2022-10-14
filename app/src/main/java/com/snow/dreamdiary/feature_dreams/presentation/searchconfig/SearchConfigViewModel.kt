package com.snow.dreamdiary.feature_dreams.presentation.searchconfig;

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.snow.dreamdiary.feature_dreams.presentation.navigation.KEY_MODE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class SearchConfigViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    init {
        Log.d("SearchConfigViewModel", ": received argument: ${savedStateHandle.get<String>(KEY_MODE)}")
    }

}