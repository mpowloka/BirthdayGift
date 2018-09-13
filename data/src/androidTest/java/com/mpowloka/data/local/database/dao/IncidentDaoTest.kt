package com.mpowloka.data.local.database.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.mpowloka.data.local.database.MainDatabase
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import com.mpowloka.data.local.database.entity.PersonIncidentLinkEntity
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@Suppress("TestFunctionName")
@RunWith(MockitoJUnitRunner::class)
class IncidentDaoTest {

    private lateinit var SUT: IncidentDao

    private lateinit var database: MainDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                MainDatabase::class.java
        ).build()

        SUT = database.incidentDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun GET_ALL_INCIDENTS_QUERY_someIncidentsInDatabase_allIncidentsReturned() {
        SUT.insert(listOf(
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40)
        ))

        val result = SUT.getAllIncidents()
        assertThat(result.size, `is`(4))
    }

    @Test
    fun GET_ALL_INCIDENTS_QUERY_noIncidentsInDatabase_emptyListReturned() {
        val result = SUT.getAllIncidents()
        assertThat(result, `is`(emptyList()))
    }


    @Test
    fun GET_INCIDENTS_FOR_PERSON_ID_QUERY_someIncidentsLinkedWithSomePersons_linkedIncidentsReturned() {
        val incidentsIds = SUT.insert(listOf(
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40)
        ))

        val personsIds = database.personsDao.insert(listOf(
                Person(firstName = "John", lastName = "Lemon"),
                Person(firstName = "John", lastName = "Lemon"),
                Person(firstName = "John", lastName = "Lemon")
        ))

        database.personIncidentLinkDao.insert(listOf(
                PersonIncidentLinkEntity(personsIds[0], incidentsIds[0]),
                PersonIncidentLinkEntity(personsIds[0], incidentsIds[1]),
                PersonIncidentLinkEntity(personsIds[1], incidentsIds[0])
        ))

        val result = SUT.getIncidentsForPersonId(personsIds[0])
        assertThat(result.size, `is`(2))
    }

    @Test
    fun GET_INCIDENTS_FOR_PERSON_ID_QUERY_noIncidentsLinkedWithPerson_emptyListReturned() {
        SUT.insert(listOf(
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40),
                Incident(name = "Incident", points = 40)
        ))
        val localPersonId = 18L

        val result = SUT.getIncidentsForPersonId(localPersonId)
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun GET_INCIDENTS_FOR_PERSON_ID_QUERY_noIncidentsInDatabase_emptyListReturned() {
        val localPersonId = 18L

        val result = SUT.getIncidentsForPersonId(localPersonId)
        assertThat(result, `is`(emptyList()))
    }

}