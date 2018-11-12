package com.mpowloka.data.local.repository.impl

import androidx.lifecycle.LiveData
import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.model.Incident
import com.mpowloka.data.local.repository.IncidentsRepository
import javax.inject.Inject

class IncidentsRepositoryImpl @Inject constructor(
        private val incidentDao: IncidentDao
) : IncidentsRepository {

    override fun getIncidentsForLocalPersonId(localPersonId: Long): List<Incident> {
        return incidentDao.getIncidentsForPersonId(localPersonId)
    }

    override fun getAllIncidentsForLocalPersonIdLiveData(localPersonId: Long): LiveData<List<Incident>> {
        return incidentDao.getIncidentsForPersonIdLiveData(localPersonId)
    }

    override fun insertIncident(incident: IncidentsEntityRow): Long {
        return incidentDao.insert(incident)
    }

    override fun insertIncidents(incidents: List<IncidentsEntityRow>): List<Long> {
        return incidentDao.insert(incidents)
    }

    override fun updateIncident(incident: IncidentsEntityRow) {
        incidentDao.update(incident)
    }

}