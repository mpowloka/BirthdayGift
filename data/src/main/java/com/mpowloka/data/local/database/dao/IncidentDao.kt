package com.mpowloka.data.local.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.Incident

@Dao
abstract class IncidentDao : BaseDao<Incident> {

    @Query(GET_ALL_INCIDENTS_QUERY)
    abstract fun getAllIncidents(): List<Incident>

    @Query(GET_ALL_INCIDENTS_QUERY)
    abstract fun getAllIncidentsLiveData(): LiveData<List<Incident>>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    abstract fun getIncidentsForPersonId(localPersonId: Long): List<Incident>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    abstract fun getIncidentsForPersonIdLiveData(localPersonId: Long): LiveData<List<Incident>>

    companion object {

        private const val GET_ALL_INCIDENTS_QUERY = "SELECT * FROM Incident"

        private const val GET_INCIDENTS_FOR_PERSON_ID_QUERY = """
            SELECT i.localId, i.name, i.description, i.points, i.creationDate
            FROM Incident i INNER JOIN PersonIncidentLink pil
            ON i.localId = pil.localIncidentId
            WHERE pil.localPersonId = :localPersonId
        """

    }

}