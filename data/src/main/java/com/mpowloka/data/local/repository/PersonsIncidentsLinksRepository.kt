package com.mpowloka.data.local.repository

import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow

interface PersonsIncidentsLinksRepository {

    fun linkPersonToIncident(person: PersonsEntityRow, incident: IncidentsEntityRow)

    fun linkPersonToIncidents(person: PersonsEntityRow, incidents: List<IncidentsEntityRow>)

    fun linkPersonsToIncident(persons: List<PersonsEntityRow>, incident: IncidentsEntityRow)

}