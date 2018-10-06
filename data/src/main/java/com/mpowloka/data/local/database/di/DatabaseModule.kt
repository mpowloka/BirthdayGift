package com.mpowloka.data.local.database.di

import android.app.Application
import androidx.room.Room
import com.mpowloka.data.local.database.MainDatabase
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
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonsDao(mainDatabase: MainDatabase) = mainDatabase.personsDao

    @Provides
    @Singleton
    fun provideIncidentssDao(mainDatabase: MainDatabase) = mainDatabase.incidentDao

    @Provides
    @Singleton
    fun providePersonsIncidentsLinkDao(mainDatabase: MainDatabase) = mainDatabase.personIncidentLinkDao

}