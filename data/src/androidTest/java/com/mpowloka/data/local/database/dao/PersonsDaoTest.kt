package com.mpowloka.data.local.database.dao

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.mpowloka.data.local.database.MainDatabase
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@Suppress("TestFunctionName")
@RunWith(MockitoJUnitRunner::class)
class PersonsDaoTest {

    private lateinit var SUT: PersonsDao

    private lateinit var database: MainDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                MainDatabase::class.java
        ).build()

        SUT = database.personsDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun GET_ALL_PERSONS_QUERY_personsInDatabase_allPersonsReturned() {
        SUT.insert(listOf(
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher")
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
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher"),
                PersonsEntityRow(firstName = "Bill", lastName = "Cypher")
        ))

        val incidentsIds = database.incidentDao.insert(listOf(
                IncidentsEntityRow(name = "Name", points = 10),
                IncidentsEntityRow(name = "Name", points = 10),
                IncidentsEntityRow(name = "Name", points = 10)
        ))

        database.personIncidentLinkDao.insert(
                PersonIncidentLinksEntityRow(personsIds[0], incidentsIds[0]),
                PersonIncidentLinksEntityRow(personsIds[0], incidentsIds[1]),
                PersonIncidentLinksEntityRow(personsIds[1], incidentsIds[1])
        )

        val result = SUT.getPersonsForIncidentId(incidentsIds[1])
        assertThat(result.size, `is`(2))
    }

    @Test
    fun GET_PERSONS_FOR_INCIDENT_ID_QUERY_noPersonsLinkedWithIncident_emptyListReturned() {
        SUT.insert(listOf(
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan"),
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan"),
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan")
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
        val firstPersonLocalId = SUT.insert(PersonsEntityRow(firstName = "Mike", lastName = "Parallax"))
        val secondPersonLocalId = SUT.insert(PersonsEntityRow(firstName = "Johny", lastName = "Bravo"))

        val firstIncidentLocalId = database.incidentDao.insert(IncidentsEntityRow(name = "Test 1", points = 42))
        val secondIncidentLocalId = database.incidentDao.insert(IncidentsEntityRow(name = "Test 2", points = 231))
        val thirdIncidentLocalId = database.incidentDao.insert(IncidentsEntityRow(name = "Test 3", points = 119))

        database.personIncidentLinkDao.insert(PersonIncidentLinksEntityRow(firstPersonLocalId, firstIncidentLocalId))
        database.personIncidentLinkDao.insert(PersonIncidentLinksEntityRow(secondPersonLocalId, secondIncidentLocalId))
        database.personIncidentLinkDao.insert(PersonIncidentLinksEntityRow(secondPersonLocalId, thirdIncidentLocalId))

        assertThat(SUT.getPersonTotalPoints(secondPersonLocalId), `is`(350))

    }

    @Test
    fun GET_ALL_PERSONS_WITH_POINTS_QUERY_noPersonsInDatabase_emptyListReturned() {
        val result = SUT.getAllPersonsWithPoints()
        assertThat(result.size, `is`(0))
    }

    @Test
    fun GET_ALL_PERSONS_WITH_POINTS_QUERY_somePersonsInDatabase_allPersonsReturned() {
        SUT.insert(listOf(
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan"),
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan"),
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan")
        ))

        val result = SUT.getAllPersonsWithPoints()
        assertThat(result.size, `is`(3))
    }

    @Test
    fun GET_ALL_PERSONS_WITH_POINTS_QUERY_personHasNoIncidents_personHasZeroPoints() {
        SUT.insert(listOf(
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan")
        ))

        val result = SUT.getAllPersonsWithPoints()
        assertThat(result[0].points, `is`(0.toLong()))
    }

    @Test
    fun GET_ALL_PERSONS_WITH_POINTS_QUERY_personHasOneIncidents_incidentPointsReturned() {
        val points = 42L

        val localPersonId = SUT.insert(
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan")
        )
        val localIncidentId = database.incidentDao.insert(
                IncidentsEntityRow(name = "Test", points = points)
        )

        database.personIncidentLinkDao.insert(
                PersonIncidentLinksEntityRow(localPersonId, localIncidentId)
        )

        val result = SUT.getAllPersonsWithPoints()
        assertThat(result[0].points, `is`(points))
    }

    @Test
    fun GET_ALL_PERSONS_WITH_POINTS_QUERY_personHasMultipleIncidents_incidentsSumOfPointsReturned() {
        val points = 42L

        val localPersonId = SUT.insert(
                PersonsEntityRow(firstName = "Bill", lastName = "Morgan")
        )
        val localIncidentIds = database.incidentDao.insert(listOf(
                IncidentsEntityRow(name = "Test", points = points),
                IncidentsEntityRow(name = "Test", points = points),
                IncidentsEntityRow(name = "Test", points = points)
        ))

        localIncidentIds.forEach {
            database.personIncidentLinkDao.insert(
                    PersonIncidentLinksEntityRow(localPersonId, it)
            )
        }

        val result = SUT.getAllPersonsWithPoints()
        assertThat(result[0].points, `is`(localIncidentIds.size * points))
    }

}