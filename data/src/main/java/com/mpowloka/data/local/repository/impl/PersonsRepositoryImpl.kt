package com.mpowloka.data.local.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.model.PersonWithPointsAndRank
import com.mpowloka.data.local.repository.PersonsRepository
import javax.inject.Inject

class PersonsRepositoryImpl @Inject constructor(
        private val personsDao: PersonsDao
) : PersonsRepository {

    override fun getAllPersonsWithPointsAndRank(): List<PersonWithPointsAndRank> {
        var rank = 1
        return personsDao.getAllPersonsWithPoints().asSequence().sortedBy { -it.points }.map {
            PersonWithPointsAndRank(it, rank++)
        }.toList()
    }

    override fun getAllPersonsWithPointsAndRankLiveData(): LiveData<List<PersonWithPointsAndRank>> {

        return Transformations.map(personsDao.getAllPersonsWithPointsLiveData()) { personsWithPoints ->
            var rank = 1
            personsWithPoints.asSequence().sortedBy { -it.points }.map {
                PersonWithPointsAndRank(it, rank++)
            }.toList()
        }
    }

    override fun getPersonWithPointsAndRankForLocalId(localPersonId: Long): PersonWithPointsAndRank? {
        var rank = 1
        return personsDao.getAllPersonsWithPoints().asSequence().sortedBy { -it.points }.map {
            PersonWithPointsAndRank(it, rank++)
        }.find { it.personWithPoints.localId == localPersonId }
    }

    override fun getPersonWithPointsAndRankForLocalIdLiveData(localPersonId: Long): LiveData<PersonWithPointsAndRank> {

        return Transformations.map(personsDao.getAllPersonsWithPointsLiveData()) { personsWithPoints ->
            var rank = 1
            personsWithPoints.asSequence().sortedBy { -it.points }.map {
                PersonWithPointsAndRank(it, rank++)
            }.find { it.personWithPoints.localId == localPersonId }
        }
    }


    override fun insertPerson(person: PersonsEntityRow): Long {
        return personsDao.insert(person)
    }

    override fun insertPersons(persons: List<PersonsEntityRow>): List<Long> {
        return personsDao.insert(persons)
    }

    override fun updatePerson(person: PersonsEntityRow) {
        personsDao.update(person)
    }

}