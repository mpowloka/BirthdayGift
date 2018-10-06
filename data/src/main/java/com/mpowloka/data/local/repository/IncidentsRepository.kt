package com.mpowloka.data.local.repository

import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import javax.inject.Inject

class IncidentsRepository @Inject constructor(
        private val incidentDao: IncidentDao
) {

    fun insertIncident(incident: IncidentsEntityRow): Long {
        return incidentDao.insert(incident)
    }

    fun insertIncidents(incidents: List<IncidentsEntityRow>): List<Long> {
        return incidentDao.insert(incidents)
    }

    fun updateIncident(incident: IncidentsEntityRow) {
        incidentDao.update(incident)
    }

}