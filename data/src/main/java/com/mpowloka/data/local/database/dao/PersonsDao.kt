package com.mpowloka.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.model.PersonWithPoints

@Dao
abstract class PersonsDao : BaseDao<PersonsEntityRow> {

    @Query(GET_ALL_PERSONS_QUERY)
    abstract fun getAllPersons(): List<PersonsEntityRow>

    @Query(GET_ALL_PERSONS_QUERY)
    abstract fun getAllPersonsLiveData(): LiveData<List<PersonsEntityRow>>

    @Query(GET_PERSONS_FOR_INCIDENT_ID_QUERY)
    abstract fun getPersonsForIncidentId(localIncidentId: Long): List<PersonsEntityRow>

    @Query(GET_PERSONS_FOR_INCIDENT_ID_QUERY)
    abstract fun getPersonsForIncidentIdLiveData(localIncidentId: Long): LiveData<List<PersonsEntityRow>>

    @Query(GET_PERSON_TOTAL_POINTS)
    abstract fun getPersonTotalPoints(localPersonId: Long): Int

    @Query(GET_PERSON_TOTAL_POINTS)
    abstract fun getPersonTotalPointsLiveData(localPersonId: Long): LiveData<Int>

    @Query(GET_ALL_PERSONS_WITH_POINTS_QUERY)
    abstract fun getAllPersonsWithPoints(): List<PersonWithPoints>

    @Query(GET_ALL_PERSONS_WITH_POINTS_QUERY)
    abstract fun getAllPersonsWithPointsLiveData(): LiveData<List<PersonWithPoints>>

    @Query(GET_PERSON_ITH_POINTS_FOR_LOCAL_ID_QUERY)
    abstract fun getPersonWithPointsForLocalId(localPersonId: Long): PersonWithPoints?

    @Query(GET_PERSON_ITH_POINTS_FOR_LOCAL_ID_QUERY)
    abstract fun getPersonWithPointsForLocalIdLiveData(localPersonId: Long): List<PersonWithPoints>

    companion object {

        private const val GET_ALL_PERSONS_QUERY = "SELECT * FROM Persons"

        private const val GET_PERSON_TOTAL_POINTS = """
            SELECT SUM(points)
            FROM Incidents i INNER JOIN PersonIncidentLinks pil
            ON i.localId = pil.localIncidentId
            WHERE pil.localPersonId = :localPersonId
        """

        private const val GET_PERSONS_FOR_INCIDENT_ID_QUERY = """
            SELECT p.*
            FROM Persons p INNER JOIN PersonIncidentLinks pil
            ON p.localId = pil.localPersonId
            WHERE pil.localIncidentId = :localIncidentId
        """

        private const val GET_POINTS_SUMS_SUB_QUERY = """
            (SELECT pil.localPersonId as localPersonId, SUM(i.points) as points
            FROM Incidents i INNER JOIN PersonIncidentLinks pil
            ON i.localId = pil.localIncidentId
            GROUP BY pil.localPersonId)
        """

        private const val GET_ALL_PERSONS_WITH_POINTS_QUERY = """
           SELECT p.*, s.points
           FROM Persons as p LEFT JOIN $GET_POINTS_SUMS_SUB_QUERY as s
           ON p.localId = s.localPersonId
        """

        private const val GET_PERSON_ITH_POINTS_FOR_LOCAL_ID_QUERY = """
           SELECT p.*, s.points
           FROM Persons as p LEFT JOIN $GET_POINTS_SUMS_SUB_QUERY as s
           ON p.localId = s.localPersonId
           WHERE p.localId = :localPersonId
        """

    }

}