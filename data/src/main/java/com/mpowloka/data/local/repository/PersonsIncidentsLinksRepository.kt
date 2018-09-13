package com.mpowloka.data.local.repository

import com.mpowloka.data.local.database.dao.PersonIncidentLinkDao
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import com.mpowloka.data.local.database.entity.PersonIncidentLinkEntity
import javax.inject.Inject

class PersonsIncidentsLinksRepository @Inject constructor(
        private val personIncidentLinkDao: PersonIncidentLinkDao
) {

    fun linkPersonToIncident(person: Person, incident: Incident) {
        personIncidentLinkDao.insert(PersonIncidentLinkEntity(person.localId, incident.localId))
    }

    fun linkPersonToIncidents(person: Person, incidents: List<Incident>) {
        val links = incidents.map { incident -> PersonIncidentLinkEntity(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

    fun linkPersonsToIncident(persons: List<Person>, incident: Incident) {
        val links = persons.map { person -> PersonIncidentLinkEntity(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

}