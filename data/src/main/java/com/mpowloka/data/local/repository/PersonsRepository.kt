package com.mpowloka.data.local.repository

import androidx.lifecycle.LiveData
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.model.PersonWithPoints
import javax.inject.Inject

class PersonsRepository @Inject constructor(
        private val personsDao: PersonsDao
) {

    fun getAllPersonsWithPoints(): List<PersonWithPoints> {
        return personsDao.getAllPersonsWithPoints()
    }

    fun getAllPersonsWithPointsLiveData(): LiveData<List<PersonWithPoints>> {
        return personsDao.getAllPersonsWithPointsLiveData()
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