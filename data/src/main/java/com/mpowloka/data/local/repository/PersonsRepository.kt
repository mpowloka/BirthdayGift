package com.mpowloka.data.local.repository

import android.arch.lifecycle.LiveData
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import javax.inject.Inject

class PersonsRepository @Inject constructor(
        private val personsDao: PersonsDao
) {

    private lateinit var allPersonsLiveData: LiveData<List<Person>>

    private val personsForIncidentsLiveDataRegistry = HashMap<Incident, LiveData<List<Person>>>()
    private val personsTotalPointsLiveDataRegistry = HashMap<Person, LiveData<Int>>()

    fun getAllPersons(): List<Person> {
        return personsDao.getAllPersons()
    }

    fun getAllPersonsLiveData(): LiveData<List<Person>> {
        if (!::allPersonsLiveData.isInitialized) {
            allPersonsLiveData = personsDao.getAllPersonsLiveData()
        }
        return allPersonsLiveData
    }

    fun getPersonsForIncident(incident: Incident): List<Person> {
        return personsDao.getPersonsForIncidentId(incident.localId)
    }

    fun getPersonsForIncidentLiveData(incident: Incident): LiveData<List<Person>> {
        return personsForIncidentsLiveDataRegistry[incident]
                ?: personsDao.getPersonsForIncidentIdLiveData(incident.localId).also {
                    personsForIncidentsLiveDataRegistry[incident] = it
                }
    }

    fun getPersonsTotalPoints(person: Person): Int {
        return personsDao.getPersonTotalPoints(person.localId)
    }

    fun getPersonsTotalPointsLiveData(person: Person): LiveData<Int> {
        return personsTotalPointsLiveDataRegistry[person]
                ?: personsDao.getPersonTotalPointsLiveData(person.localId).also {
                    personsTotalPointsLiveDataRegistry[person] = it
                }
    }

    fun insertPerson(person: Person): Long {
        return personsDao.insert(person)
    }

    fun insertPersons(persons: List<Person>): List<Long> {
        return personsDao.insert(persons)
    }

    fun updatePerson(person: Person) {
        personsDao.update(person)
    }

}