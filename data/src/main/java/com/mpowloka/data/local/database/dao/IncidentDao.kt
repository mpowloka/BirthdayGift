package com.mpowloka.data.local.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.IncidentEntity

@Dao
abstract class IncidentDao : BaseDao<IncidentEntity> {

    @Query(GET_ALL_INCIDENTS_QUERY)
    abstract fun getAllIncidents(): List<IncidentEntity>

    @Query(GET_ALL_INCIDENTS_QUERY)
    abstract fun getAllIncidentsLiveData(): LiveData<List<IncidentEntity>>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    abstract fun getIncidentsForPersonId(localPersonId: Long): List<IncidentEntity>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    abstract fun getIncidentsForPersonIdLiveData(localPersonId: Long): LiveData<List<IncidentEntity>>

    companion object {

        private const val GET_ALL_INCIDENTS_QUERY = "SELECT * FROM Incidents"

        private const val GET_INCIDENTS_FOR_PERSON_ID_QUERY = """
            SELECT i.localId, i.name, i.description, i.points, i.pictureUrl, i.creationDate, i.deleted
            FROM Incidents i INNER JOIN PersonIncidentLinks pil
            ON i.localId = pil.localIncidentId
            WHERE pil.localPersonId = :localPersonId
        """

    }

}