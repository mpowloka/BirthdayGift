package com.mpowloka.data.local.database.di

import android.app.Application
import androidx.room.Room
import com.mpowloka.data.local.database.MainDatabase
import com.mpowloka.data.local.database.TestDataPreloadCallback
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMainDatabase(application: Application): MainDatabase {
        return Room.databaseBuilder(
                application.applicationContext,
                MainDatabase::class.java,
                MainDatabase.DATABASE_NAME
        )
                .addCallback(TestDataPreloadCallback()) //TODO remove after development
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun providePersonsDao(mainDatabase: MainDatabase) = mainDatabase.personsDao

    @Provides
    @Singleton
    fun provideIncidentsDao(mainDatabase: MainDatabase) = mainDatabase.incidentDao

    @Provides
    @Singleton
    fun providePersonsIncidentsLinkDao(mainDatabase: MainDatabase) = mainDatabase.personIncidentLinkDao

}