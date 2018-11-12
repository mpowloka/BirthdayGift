package com.mpowloka.data.local.repository

import androidx.lifecycle.LiveData
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.model.PersonWithPointsAndRank

interface PersonsRepository {

    fun getAllPersonsWithPointsAndRank(): List<PersonWithPointsAndRank>

    fun getAllPersonsWithPointsAndRankLiveData(): LiveData<List<PersonWithPointsAndRank>>

    fun getPersonWithPointsAndRankForLocalId(localPersonId: Long): PersonWithPointsAndRank?

    fun getPersonWithPointsAndRankForLocalIdLiveData(localPersonId: Long): LiveData<PersonWithPointsAndRank>


    fun insertPerson(person: PersonsEntityRow): Long

    fun insertPersons(persons: List<PersonsEntityRow>): List<Long>

    fun updatePerson(person: PersonsEntityRow)

}