package com.mpowloka.data.local.di

import com.mpowloka.data.local.repository.IncidentsRepository
import com.mpowloka.data.local.repository.PersonsIncidentsLinksRepository
import com.mpowloka.data.local.repository.PersonsRepository
import com.mpowloka.data.local.repository.impl.IncidentsRepositoryImpl
import com.mpowloka.data.local.repository.impl.PersonsIncidentsLinksRepositoryImpl
import com.mpowloka.data.local.repository.impl.PersonsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun personsRepository(personsRepositoryImpl: PersonsRepositoryImpl): PersonsRepository

    @Binds
    abstract fun incidentsRepository(incidentsRepositoryImpl: IncidentsRepositoryImpl): IncidentsRepository

    @Binds
    abstract fun personsIncidentsLinksRepository(
            personsIncidentsLinksRepositoryImpl: PersonsIncidentsLinksRepositoryImpl
    ): PersonsIncidentsLinksRepository

}