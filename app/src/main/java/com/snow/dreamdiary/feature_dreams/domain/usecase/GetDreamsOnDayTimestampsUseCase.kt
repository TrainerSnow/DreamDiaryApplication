package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import kotlinx.coroutines.flow.first

public class GetDreamsOnDayTimestampsUseCase(
    private val getDreamsUseCase: GetDreamsUseCase
) {
    suspend operator fun invoke(_timestamps: List<Long>): List<Dream>{
        val timestamps = _timestamps.map { TimeUtil.dayRangeFromMillis(it) }

        return getDreamsUseCase().first().filter {
            timestamps.any { stamp ->
                it.dreamtAt in stamp
            }
        }
    }
}