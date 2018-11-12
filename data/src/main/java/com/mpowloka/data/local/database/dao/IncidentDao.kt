package com.mpowloka.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.model.Incident

@Dao
abstract class IncidentDao : BaseDao<IncidentsEntityRow> {

    @Query(GET_ALL_INCIDENTS_QUERY)
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    abstract fun getAllIncidents(): List<Incident>

    @Query(GET_ALL_INCIDENTS_QUERY)
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    abstract fun getAllIncidentsLiveData(): LiveData<List<Incident>>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    abstract fun getIncidentsForPersonIdLiveData(localPersonId: Long): LiveData<List<Incident>>

    @Query(GET_INCIDENTS_FOR_PERSON_ID_QUERY)
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    abstract fun getIncidentsForPersonId(localPersonId: Long): List<Incident>

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