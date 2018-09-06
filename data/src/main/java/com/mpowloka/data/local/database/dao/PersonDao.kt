package com.mpowloka.data.local.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.Person

@Dao
abstract class PersonDao : BaseDao<Person> {

    @Query(GET_ALL_PERSONS_QUERY)
    abstract fun getAllPersons(): List<Person>

    @Query(GET_ALL_PERSONS_QUERY)
    abstract fun getAllPersonsLiveData(): LiveData<List<Person>>

    @Query(GET_PERSONS_FOR_INCIDENT_ID_QUERY)
    abstract fun getPersonsForIncidentId(localIncidentId: Long): List<Person>

    @Query(GET_PERSONS_FOR_INCIDENT_ID_QUERY)
    abstract fun getPersonsForIncidentIdLiveData(localIncidentId: Long): LiveData<List<Person>>

    companion object {

        private const val GET_ALL_PERSONS_QUERY = "SELECT * FROM Person"

        private const val GET_PERSONS_FOR_INCIDENT_ID_QUERY = """
            SELECT p.localId, p.firstName, p.lastName
            FROM Person p INNER JOIN PersonIncidentLink pil
            ON p.localId = pil.localPersonId
            WHERE pil.localIncidentId = :localIncidentId
        """

    }

}