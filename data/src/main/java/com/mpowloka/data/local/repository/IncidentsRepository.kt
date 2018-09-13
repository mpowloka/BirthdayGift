package com.mpowloka.data.local.repository

import android.arch.lifecycle.LiveData
import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import javax.inject.Inject

class IncidentsRepository @Inject constructor(
        private val incidentDao: IncidentDao
) {

    private lateinit var allIncidentsLiveData: LiveData<List<Incident>>

    private val incidentsForPersonsLiveDataRegistry = HashMap<Person, LiveData<List<Incident>>>()

    fun getAllIncidents(): List<Incident> {
        return incidentDao.getAllIncidents()
    }

    fun getAllIncidentsLiveData(): LiveData<List<Incident>> {
        if (!::allIncidentsLiveData.isInitialized) {
            allIncidentsLiveData = incidentDao.getAllIncidentsLiveData()
        }
        return allIncidentsLiveData
    }

    fun getIncidentsForPerson(person: Person): List<Incident> {
        return incidentDao.getIncidentsForPersonId(person.localId)
    }

    fun getIncidentsForPersonLiveData(person: Person): LiveData<List<Incident>> {
        return incidentsForPersonsLiveDataRegistry[person]
                ?: incidentDao.getIncidentsForPersonIdLiveData(person.localId).also {
                    incidentsForPersonsLiveDataRegistry[person] = it
                }
    }

    fun insertIncident(incident: Incident): Long {
        return incidentDao.insert(incident)
    }

    fun insertIncidents(incidents: List<Incident>): List<Long> {
        return incidentDao.insert(incidents)
    }

    fun updateIncident(incident: Incident) {
        incidentDao.update(incident)
    }

}