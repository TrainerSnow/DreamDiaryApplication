package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

public class GetDreamsForSearchConfig(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(searchMode: DreamSearchModes): List<Dream>{

    }
}