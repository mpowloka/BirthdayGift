package com.mpowloka.data.local.repository

import com.mpowloka.data.local.database.dao.PersonIncidentLinkDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import javax.inject.Inject

class PersonsIncidentsLinksRepository @Inject constructor(
        private val personIncidentLinkDao: PersonIncidentLinkDao
) {

    fun linkPersonToIncident(person: PersonsEntityRow, incident: IncidentsEntityRow) {
        personIncidentLinkDao.insert(PersonIncidentLinksEntityRow(person.localId, incident.localId))
    }

    fun linkPersonToIncidents(person: PersonsEntityRow, incidents: List<IncidentsEntityRow>) {
        val links = incidents.map { incident -> PersonIncidentLinksEntityRow(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

    fun linkPersonsToIncident(persons: List<PersonsEntityRow>, incident: IncidentsEntityRow) {
        val links = persons.map { person -> PersonIncidentLinksEntityRow(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

}