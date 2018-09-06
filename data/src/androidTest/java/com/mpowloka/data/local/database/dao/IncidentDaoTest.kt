package com.mpowloka.data.local.database.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.mpowloka.data.local.database.MainDatabase
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import com.mpowloka.data.local.database.entity.PersonIncidentLink
import org.awaitility.Awaitility.await
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class IncidentDaoTest {

    private lateinit var SUT: IncidentDao

    private lateinit var personIncidentLinkDao: PersonIncidentLinkDao

    private lateinit var personDao: PersonDao

    @Before
    fun setup() {
        val database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                MainDatabase::class.java
        ).build()

        personDao = database.personDao
        personIncidentLinkDao = database.personIncidentLinkDao
        SUT = database.incidentDao
    }

    @Test
    fun getAllIncidents_incidentsInDatabase_allIncidentsReturned() {
        val incidents = insertSomeIncidents()

        val result = SUT.getAllIncidents()
        assertThat(result, `is`(incidents))
    }

    @Test
    fun getAllIncidents_noIncidentsInDatabase_emptyListReturned() {
        val result = SUT.getAllIncidents()
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getAllIncidentsLiveData_incidentsInDatabase_allIncidentsReturned() {
        val incidents = insertSomeIncidents()

        val result = SUT.getAllIncidentsLiveData().also { it.observeForever { _ -> } }
        await().atMost(2, TimeUnit.SECONDS).until {
            result.value == incidents
        }
    }

    @Test
    fun getAllIncidentsLiveData_noIncidentsInDatabase_nullOrEmptyListReturned() {
        val result = SUT.getAllIncidentsLiveData()
        result.observeForever { incidentsFromDatabase ->
            if (incidentsFromDatabase != null && incidentsFromDatabase.isNotEmpty()) {
                fail()
            }
        }
    }

    @Test
    fun getIncidentsForPersonId_someIncidentsLinkedWithPerson_linkedIncidentsReturned() {
        val incidents = insertSomeIncidents()
        val localPersonId = 18L

        linkIncidentsToPerson(incidents.filter { it.localId > 2 }, localPersonId)

        val result = SUT.getIncidentsForPersonId(localPersonId)
        assertThat(result, `is`(incidents.filter { it.localId > 2 }))
    }

    @Test
    fun getIncidentsForPersonId_noIncidentsLinkedWithPerson_emptyListReturned() {
        insertSomeIncidents()
        val localPersonId = 18L

        val result = SUT.getIncidentsForPersonId(localPersonId)
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getIncidentsForPersonId_noIncidentsInDatabase_emptyListReturned() {
        val localPersonId = 18L

        val result = SUT.getIncidentsForPersonId(localPersonId)
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getIncidentsForPersonIdLiveData_someIncidentsLinkedWithPerson_linkedIncidentsReturned() {
        val incidents = insertSomeIncidents()
        val localPersonId = 18L

        linkIncidentsToPerson(incidents.filter { it.localId > 2 }, localPersonId)

        val result = SUT.getIncidentsForPersonIdLiveData(localPersonId).also { it.observeForever { _ -> } }
        await().atMost(2, TimeUnit.SECONDS).until {
            result.value == incidents.filter { it.localId > 2 }
        }
    }

    @Test
    fun getIncidentsForPersonIdLiveData_noIncidentsLinkedWithPerson_emptyListReturned() {
        insertSomeIncidents()
        val localPersonId = 18L

        SUT.getIncidentsForPersonIdLiveData(localPersonId).also {
            it.observeForever { personsFromDatabase ->
                if (personsFromDatabase != null && personsFromDatabase.isNotEmpty()) {
                    fail()
                }
            }
        }
    }

    @Test
    fun getIncidentsForPersonIdLiveData_noIncidentsInDatabase_emptyListReturned() {
        val incidentId = 18L

        SUT.getIncidentsForPersonIdLiveData(incidentId).also {
            it.observeForever { personsFromDatabase ->
                if (personsFromDatabase != null && personsFromDatabase.isNotEmpty()) {
                    fail()
                }
            }
        }
    }

    private fun linkIncidentsToPerson(incidents: List<Incident>, localPersonId: Long) {
        val links = mutableListOf<PersonIncidentLink>()
        incidents.forEach { incident ->
            links.add(PersonIncidentLink(localPersonId, incident.localId))
        }
        personDao.insert(Person(localPersonId, "Laura", "Colt"))
        personIncidentLinkDao.insert(links)
    }

    private fun insertSomeIncidents(): List<Incident> {
        val incidents = listOf(
                Incident(1, "Nice meal", points = 13),
                Incident(2, "I enjoyed conversation", points = 133),
                Incident(3, "Amazing dress", points = 55),
                Incident(4, "Smooth voice...", points = 83)
        )
        SUT.insert(incidents)
        return incidents
    }
}