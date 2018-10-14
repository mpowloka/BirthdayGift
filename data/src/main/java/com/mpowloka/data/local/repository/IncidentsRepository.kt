package com.mpowloka.data.local.repository

import androidx.lifecycle.LiveData
import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.model.Incident
import javax.inject.Inject

class IncidentsRepository @Inject constructor(
        private val incidentDao: IncidentDao
) {

    fun getIncidentsForLocalPersonId(localPersonId: Long): List<Incident> {
        return incidentDao.getIncidentsForPersonId(localPersonId)
    }

    fun getAllIncidentsForLocalPersonIdLiveData(localPersonId: Long): LiveData<List<Incident>> {
        return incidentDao.getIncidentsForPersonIdLiveData(localPersonId)
    }

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