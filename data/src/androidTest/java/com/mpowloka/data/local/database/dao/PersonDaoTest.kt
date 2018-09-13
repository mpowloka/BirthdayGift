package com.mpowloka.data.local.database.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.mpowloka.data.local.database.MainDatabase
import com.mpowloka.data.local.database.entity.IncidentEntity
import com.mpowloka.data.local.database.entity.PersonEntity
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
class PersonDaoTest {

    private lateinit var SUT: PersonDao

    private lateinit var database: MainDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                MainDatabase::class.java
        ).build()

        SUT = database.personDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun GET_ALL_PERSONS_QUERY_personsInDatabase_allPersonsReturned() {
        SUT.insert(listOf(
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher")
        ))

        val result = SUT.getAllPersons()
        assertThat(result.size, `is`(4))
    }

    @Test
    fun GET_ALL_PERSONS_QUERY_noPersonsInDatabase_emptyListReturned() {
        val result = SUT.getAllPersons()
        assertThat(result.size, `is`(0))
    }

    @Test
    fun GET_PERSONS_FOR_INCIDENT_ID_QUERY_somePersonsLinkedWithIncident_linkedPersonsReturned() {

        val personsIds = SUT.insert(listOf(
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher"),
                PersonEntity(firstName = "Bill", lastName = "Cypher")
        ))

        val incidentsIds = database.incidentDao.insert(listOf(
                IncidentEntity(name = "Name", points = 10),
                IncidentEntity(name = "Name", points = 10),
                IncidentEntity(name = "Name", points = 10)
        ))

        database.personIncidentLinkDao.insert(
                PersonIncidentLinkEntity(personsIds[0], incidentsIds[0]),
                PersonIncidentLinkEntity(personsIds[0], incidentsIds[1]),
                PersonIncidentLinkEntity(personsIds[1], incidentsIds[1])
        )

        val result = SUT.getPersonsForIncidentId(incidentsIds[1])
        assertThat(result.size, `is`(2))
    }

    @Test
    fun GET_PERSONS_FOR_INCIDENT_ID_QUERY_noPersonsLinkedWithIncident_emptyListReturned() {
        SUT.insert(listOf(
                PersonEntity(firstName = "Bill", lastName = "Morgan"),
                PersonEntity(firstName = "Bill", lastName = "Morgan"),
                PersonEntity(firstName = "Bill", lastName = "Morgan")
        ))
        val localIncidentId = 18L

        val result = SUT.getPersonsForIncidentId(localIncidentId)
        assertThat(result.size, `is`(0))
    }


    @Test
    fun GET_PERSONS_FOR_INCIDENT_ID_QUERY_noPersonsInDatabase_emptyListReturned() {
        val localIncidentId = 18L

        val result = SUT.getPersonsForIncidentId(localIncidentId)
        assertThat(result, `is`(emptyList()))
    }


    @Test
    fun GET_PERSON_TOTAL_POINTS_multiplePersonsAndIncidents_sumOfPersonsIncidentsPointsReturned() {
        val firstPersonLocalId = SUT.insert(PersonEntity(firstName = "Mike", lastName = "Parallax"))
        val secondPersonLocalId = SUT.insert(PersonEntity(firstName = "Johny", lastName = "Bravo"))

        val firstIncidentLocalId = database.incidentDao.insert(IncidentEntity(name = "Test 1", points = 42))
        val secondIncidentLocalId = database.incidentDao.insert(IncidentEntity(name = "Test 2", points = 231))
        val thirdIncidentLocalId = database.incidentDao.insert(IncidentEntity(name = "Test 3", points = 119))

        database.personIncidentLinkDao.insert(PersonIncidentLinkEntity(firstPersonLocalId, firstIncidentLocalId))
        database.personIncidentLinkDao.insert(PersonIncidentLinkEntity(secondPersonLocalId, secondIncidentLocalId))
        database.personIncidentLinkDao.insert(PersonIncidentLinkEntity(secondPersonLocalId, thirdIncidentLocalId))

        assertThat(SUT.getPersonTotalPoints(secondPersonLocalId), `is`(350))

    }

}