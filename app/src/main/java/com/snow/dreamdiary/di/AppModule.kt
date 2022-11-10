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
        val getDreams = GetDreamsUseCase(repository) //
        val deleteDreams = DeleteDreamUseCase(repository) //
        val addDream = AddDreamUseCase(repository) //
        val editDream = EditDreamUseCase(repository) //
        val getNewFeelings = GetNewFeelingsUseCase(repository) //
        val getNewLocations = GetNewLocationsUseCase(repository)//
        val getNewPersons = GetNewPersonsUseCase(repository) //
        val getFeelings = GetFeelingsUseCase(repository) //
        val getLocations = GetLocationsUseCase(repository) //
        val getPersons = GetPersonsUseCase(repository) //
        val getDreamById = FindDreamByIdUseCase(repository) //
        val getDreamsForSearchConfig = GetDreamsForSearchConfigUseCase(repository) //
        val getModifiersInTime = GetModifiersInTimeUseCase(repository) //
        val getComfortnesses = GetComfortnessesUseCase(repository) //
        val getModifierTimeStamps = GetModifierTimeStampsUseCase(repository) //
        val getDreamTimeStamps = GetDreamTimeStampsUseCase(repository) //
        val getComfortnessTimeStamps = GetComfortnessTimeStampsUseCase(repository) //
        val getEarliestDreamTimeStamp = GetEarliestDreamTimeStampUseCase(repository) //
        val deleteAllDreams = DeleteAllDreamsUseCase(repository) //


        val getDreamsOnDayTimestampsUseCase = GetDreamsOnDayTimestampsUseCase(
            getDreamsUseCase = getDreams
        ) //

        val getDreamsAffectablesAverage = GetDreamAffectablesAverageUseCase(
            getDreamsUseCase = getDreams,
            getEarliestDreamTimeStampUseCase = getEarliestDreamTimeStamp,
            getDreamsOnDayTimestampsUseCase = getDreamsOnDayTimestampsUseCase
        ) //

        return DreamUseCases(
            getDreams = getDreams,
            deleteDreams = deleteDreams,
            addDream = addDream,
            editDream = editDream,
            getNewFeelings = getNewFeelings,
            getNewLocations = getNewLocations,
            getNewPersons = getNewPersons,
            getFeelings = getFeelings,
            getLocations = getLocations,
            getPersons = getPersons,
            getDreamById = getDreamById,
            getDreamsForSearchConfig = getDreamsForSearchConfig,
            getModifiersInTime = getModifiersInTime,
            getComfortnesses = getComfortnesses,
            getModifierTimeStamps = getModifierTimeStamps,
            getDreamTimeStamps = getDreamTimeStamps,
            getComfortnessTimeStamps = getComfortnessTimeStamps,
            getEarliestDreamTimeStamp = getEarliestDreamTimeStamp,
            getDreamsAffectablesAverage = getDreamsAffectablesAverage,
            getDreamsOnDayTimestampsUseCase = getDreamsOnDayTimestampsUseCase,
            deleteAllDreamsUseCase = deleteAllDreams
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
        val addSurvey = AddSurveyUseCase(repository)
        val getSurveys = GetSurveysUseCase(repository)
        val getDailySurveyPartAverage = GetDailySurveyPartAverageUseCase(getSurveys)
        val getSurveyPartsOverAverage = GetSurveyPartsOverAverage(
            getSurveysUseCase = getSurveys,
            getDailySurveyPartAverageUseCase = getDailySurveyPartAverage
        )
        val getSurveyPartsBelowAverage = GetSurveyPartsBelowAverage(
            getSurveysUseCase = getSurveys,
            getDailySurveyPartAverageUseCase = getDailySurveyPartAverage
        )
        val getEffectiveSurveyData = GetEffectiveSurveyDataUseCase(GetSurveysUseCase(repository))
        val getTimeStampsDailySurveyPartOverAverage = GetTimeStampsDailySurveyPartOverAverage(
            getDailySurveyPartAverageUseCase = getDailySurveyPartAverage,
            getSurveysUseCase = getSurveys
        )
        val deleteAllSurveysUseCase = DeleteAllSurveysUseCase(repository)

        return SurveyUseCases(
            addSurvey = addSurvey, //
            getSurveys = getSurveys, //
            getDailySurveyPartAverage = getDailySurveyPartAverage, //
            getSurveyPartsOverAverage = getSurveyPartsOverAverage, //
            getSurveyPartsBelowAverage = getSurveyPartsBelowAverage, //
            getEffectiveSurveyData = getEffectiveSurveyData, //
            getTimeStampsDailySurveyPartOverAverage = getTimeStampsDailySurveyPartOverAverage, //
            deleteAllSurveysUseCase = deleteAllSurveysUseCase //
        )
    }

    @Provides
    @Singleton
    fun provideDreamSurveyUseCases(
        surveyRepo: DailySurveyRepository,
        dreamRepo: DreamRepository
    ): DreamSurveyUseCases {

        val getSurveysUseCase = GetSurveysUseCase(surveyRepo)

        val getDreamsUseCase = GetDreamsUseCase(dreamRepo)

        val getDailySurveyPartAverageUseCase = GetDailySurveyPartAverageUseCase(
            getSurveysUseCase = getSurveysUseCase
        )

        val getEarliestDreamTimeStampUseCase = GetEarliestDreamTimeStampUseCase(dreamRepo)

        val getTimeStampsDailySurveyPartOverAverage = GetTimeStampsDailySurveyPartOverAverage(
            getDailySurveyPartAverageUseCase = getDailySurveyPartAverageUseCase,
            getSurveysUseCase = getSurveysUseCase
        )

        val getDreamsOnDayTimestampsUseCase = GetDreamsOnDayTimestampsUseCase(getDreamsUseCase)

        val getDreamAffectablesAverageUseCase = GetDreamAffectablesAverageUseCase(
            getDreamsUseCase = getDreamsUseCase,
            getEarliestDreamTimeStampUseCase = getEarliestDreamTimeStampUseCase,
            getDreamsOnDayTimestampsUseCase = getDreamsOnDayTimestampsUseCase
        )

        val getAffectableAvgSpecificDays = GetAffectableAvgSpecificDays(
            getTimeStampsDailySurveyPartOverAverage = getTimeStampsDailySurveyPartOverAverage,
            getDreamAffectablesAverageUseCase = getDreamAffectablesAverageUseCase
        )

        val getPercentChangeAffectableBySurveyDataPart = GetPercentChangeAffectableBySurveyDataPart(
            getDailySurveyPartAverageUseCase = getDailySurveyPartAverageUseCase,
            getAffectableAvgSpecificDays = getAffectableAvgSpecificDays
        )

        return DreamSurveyUseCases(
            getAffectableAvgSpecificDays = getAffectableAvgSpecificDays,
            getPercentChangeAffectableBySurveyDataPart = getPercentChangeAffectableBySurveyDataPart
        )
    }
}