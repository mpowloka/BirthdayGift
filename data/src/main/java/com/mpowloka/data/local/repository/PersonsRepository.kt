package com.mpowloka.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.model.PersonWithPointsAndRank
import javax.inject.Inject

class PersonsRepository @Inject constructor(
        private val personsDao: PersonsDao
) {

    fun getAllPersonsWithPointsAndRank(): List<PersonWithPointsAndRank> {
        var rank = 1
        return personsDao.getAllPersonsWithPoints().asSequence().sortedBy { -it.points }.map {
            PersonWithPointsAndRank(it, rank++)
        }.toList()
    }

    fun getAllPersonsWithPointsAndRankLiveData(): LiveData<List<PersonWithPointsAndRank>> {

        return Transformations.map(personsDao.getAllPersonsWithPointsLiveData()) { personsWithPoints ->
            var rank = 1
            personsWithPoints.asSequence().sortedBy { -it.points }.map {
                PersonWithPointsAndRank(it, rank++)
            }.toList()
        }
    }

    fun getPersonWithPointsAndRankForLocalId(localPersonId: Long): PersonWithPointsAndRank? {
        var rank = 1
        return personsDao.getAllPersonsWithPoints().asSequence().sortedBy { -it.points }.map {
            PersonWithPointsAndRank(it, rank++)
        }.find { it.personWithPoints.localId == localPersonId }
    }

    fun getPersonWithPointsAndRankForLocalIdLiveData(localPersonId: Long): LiveData<PersonWithPointsAndRank> {

        return Transformations.map(personsDao.getAllPersonsWithPointsLiveData()) { personsWithPoints ->
            var rank = 1
            personsWithPoints.asSequence().sortedBy { -it.points }.map {
                PersonWithPointsAndRank(it, rank++)
            }.find { it.personWithPoints.localId == localPersonId }
        }
    }


    fun insertPerson(person: PersonsEntityRow): Long {
        return personsDao.insert(person)
    }

    fun insertPersons(persons: List<PersonsEntityRow>): List<Long> {
        return personsDao.insert(persons)
    }

    fun updatePerson(person: PersonsEntityRow) {
        personsDao.update(person)
    }

}