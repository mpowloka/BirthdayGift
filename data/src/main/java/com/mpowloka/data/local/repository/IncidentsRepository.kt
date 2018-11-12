package com.mpowloka.data.local.repository

import androidx.lifecycle.LiveData
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.model.Incident

interface IncidentsRepository {

    fun getIncidentsForLocalPersonId(localPersonId: Long): List<Incident>

    fun getAllIncidentsForLocalPersonIdLiveData(localPersonId: Long): LiveData<List<Incident>>

    fun insertIncident(incident: IncidentsEntityRow): Long

    fun insertIncidents(incidents: List<IncidentsEntityRow>): List<Long>

    fun updateIncident(incident: IncidentsEntityRow)

}