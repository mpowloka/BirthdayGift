package com.mpowloka.data.local.repository.impl

import com.mpowloka.data.local.database.dao.PersonIncidentLinkDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.repository.PersonsIncidentsLinksRepository
import javax.inject.Inject

class PersonsIncidentsLinksRepositoryImpl @Inject constructor(
        private val personIncidentLinkDao: PersonIncidentLinkDao
) : PersonsIncidentsLinksRepository {

    override fun linkPersonToIncident(person: PersonsEntityRow, incident: IncidentsEntityRow) {
        personIncidentLinkDao.insert(PersonIncidentLinksEntityRow(person.localId, incident.localId))
    }

    override fun linkPersonToIncidents(person: PersonsEntityRow, incidents: List<IncidentsEntityRow>) {
        val links = incidents.map { incident -> PersonIncidentLinksEntityRow(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

    override fun linkPersonsToIncident(persons: List<PersonsEntityRow>, incident: IncidentsEntityRow) {
        val links = persons.map { person -> PersonIncidentLinksEntityRow(person.localId, incident.localId) }
        personIncidentLinkDao.insert(links)
    }

}