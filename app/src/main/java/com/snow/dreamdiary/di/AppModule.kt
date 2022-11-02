package com.snow.dreamdiary.di

import android.app.Application
import androidx.room.Room
import com.snow.dreamdiary.feature_dreams.data.repository.DailySurveyRepositoryImpl
import com.snow.dreamdiary.feature_dreams.data.repository.DreamRepositoryImpl
import com.snow.dreamdiary.feature_dreams.data.source.DailySurveyDataDatabase
import com.snow.dreamdiary.feature_dreams.data.source.DreamDatabase
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import com.snow.dreamdiary.feature_dreams.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDreamDatabase(app: Application): DreamDatabase {
        return Room.databaseBuilder(
            app,
            DreamDatabase::class.java,
            DreamDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDreamRepository(db: DreamDatabase): DreamRepository {
        return DreamRepositoryImpl(db.dreamDao)
    }

    @Provides
    @Singleton
    fun provideDreamUseCases(repository: DreamRepository): DreamUseCases {
        return DreamUseCases(
            getDreams = GetDreamsUseCase(repository),
            deleteDreams = DeleteDreamUseCase(repository),
            addDream = AddDreamUseCase(repository),
            editDream = EditDreamUseCase(repository),
            getNewFeelings = GetNewFeelingsUseCase(repository),
            getNewLocations = GetNewLocationsUseCase(repository),
            getNewPersons = GetNewPersonsUseCase(repository),
            getFeelings = GetFeelingsUseCase(repository),
            getLocations = GetLocationsUseCase(repository),
            getPersons = GetPersonsUseCase(repository),
            getDreamById = FindDreamByIdUseCase(repository),
            getDreamsForSearchConfig = GetDreamsForSearchConfigUseCase(repository),
            getModifiersInTime = GetModifiersInTimeUseCase(repository),
            getComfortnesses = GetComfortnessesUseCase(repository),
            getModifierTimeStamps = GetModifierTimeStampsUseCase(repository),
            getDreamTimeStamps = GetDreamTimeStampsUseCase(repository),
            getComfortnessTimeStamps = GetComfortnessTimeStampsUseCase(repository),
            getEarliestDreamTimeStamp = GetEarliestDreamTimeStampUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDailySurveyDatabase(app: Application): DailySurveyDataDatabase {
        return Room.databaseBuilder(
            app,
            DailySurveyDataDatabase::class.java,
            DailySurveyDataDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSurveyRepository(db: DailySurveyDataDatabase): DailySurveyRepository {
        return DailySurveyRepositoryImpl(db.dailySurveyDataDao)
    }

    @Provides
    @Singleton
    fun provideSurveyUseCases(repository: DailySurveyRepository): SurveyUseCases {
        return SurveyUseCases(
            addSurvey = AddSurveyUseCase(repository),
            getSurveys = GetSurveysUseCase(repository),
            getDailySurveyPartAverage = GetDailySurveyPartAverageUseCase(
                GetSurveysUseCase(repository)
            ),
            getSurveyPartsOverAverage = GetSurveyPartsOverAverage(
                GetSurveysUseCase(repository),
                GetDailySurveyPartAverageUseCase(
                    GetSurveysUseCase(
                        repository
                    )
                )
            ),
            getSurveyPartsBelowAverage = GetSurveyPartsBelowAverage(
                GetSurveysUseCase(repository),
                GetDailySurveyPartAverageUseCase(
                    GetSurveysUseCase(
                        repository
                    )
                )
            )
        )
    }
}