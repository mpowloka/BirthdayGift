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
class PersonDaoTest {

    private lateinit var SUT: PersonDao

    private lateinit var personIncidentLinkDao: PersonIncidentLinkDao

    private lateinit var incidentDao: IncidentDao

    @Before
    fun setup() {
        val database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                MainDatabase::class.java
        ).build()

        incidentDao = database.incidentDao
        personIncidentLinkDao = database.personIncidentLinkDao
        SUT = database.personDao
    }

    @Test
    fun getAllPersons_personsInDatabase_allPersonsReturned() {
        val persons = insertSomePersons()

        val result = SUT.getAllPersons()
        assertThat(result, `is`(persons))
    }

    @Test
    fun getAllPersons_noPersonsInDatabase_emptyListReturned() {
        val result = SUT.getAllPersons()
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getAllPersonsLiveData_personsInDatabase_allPersonsReturned() {
        val persons = insertSomePersons()

        val result = SUT.getAllPersonsLiveData().also { it.observeForever { _ -> } }
        await().atMost(2, TimeUnit.SECONDS).until {
            result.value == persons
        }
    }

    @Test
    fun getAllPersonsLiveData_noPersonsInDatabase_nullOrEmptyListReturned() {
        val result = SUT.getAllPersonsLiveData()
        result.observeForever { personsFromDatabase ->
            if (personsFromDatabase != null && personsFromDatabase.isNotEmpty()) {
                fail()
            }
        }
    }

    @Test
    fun getPersonsForIncidentId_somePersonsLinkedWithIncident_linkedPersonsReturned() {
        val persons = insertSomePersons()
        val localIncidentId = 18L

        linkPersonsToIncident(persons.filter { it.localId > 2 }, localIncidentId)

        val result = SUT.getPersonsForIncidentId(localIncidentId)
        assertThat(result, `is`(persons.filter { it.localId > 2 }))
    }

    @Test
    fun getPersonsForIncidentIdL_noPersonsLinkedWithIncident_emptyListReturned() {
        insertSomePersons()
        val localIncidentId = 18L

        val result = SUT.getPersonsForIncidentId(localIncidentId)
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getPersonsForIncidentId_noPersonsInDatabase_emptyListReturned() {
        val localIncidentId = 18L

        val result = SUT.getPersonsForIncidentId(localIncidentId)
        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun getPersonsForIncidentIdLiveData_somePersonsLinkedWithIncident_linkedPersonsReturned() {
        val persons = insertSomePersons()
        val localIncidentId = 18L

        linkPersonsToIncident(persons.filter { it.localId > 2 }, localIncidentId)

        val result = SUT.getPersonsForIncidentIdLiveData(localIncidentId).also { it.observeForever { _ -> } }
        await().atMost(2, TimeUnit.SECONDS).until {
            result.value == persons.filter { it.localId > 2 }
        }
    }

    @Test
    fun getPersonsForIncidentIdLiveData_noPersonsLinkedWithIncident_nullOrEmptyListReturned() {
        insertSomePersons()
        val localIncidentId = 18L

        SUT.getPersonsForIncidentIdLiveData(localIncidentId).also {
            it.observeForever { personsFromDatabase ->
                if (personsFromDatabase != null && personsFromDatabase.isNotEmpty()) {
                    fail()
                }
            }
        }
    }

    @Test
    fun getPersonsForIncidentIdLiveData_noPersonsInDatabase_nullOrEmptyListReturned() {
        val localIncidentId = 18L

        SUT.getPersonsForIncidentIdLiveData(localIncidentId).also {
            it.observeForever { personsFromDatabase ->
                if (personsFromDatabase != null && personsFromDatabase.isNotEmpty()) {
                    fail()
                }
            }
        }
    }

    private fun linkPersonsToIncident(persons: List<Person>, incidentLocalId: Long) {
        val links = mutableListOf<PersonIncidentLink>()
        persons.forEach { person ->
            links.add(PersonIncidentLink(person.localId, incidentLocalId))
        }
        incidentDao.insert(Incident(incidentLocalId, "Incident", points = 42))
        personIncidentLinkDao.insert(links)
    }

    private fun insertSomePersons(): List<Person> {
        val persons = listOf(
                Person(1, "Tommy", "Belly"),
                Person(2, "Bill", "Cypher"),
                Person(3, "Anthony", "Hopkins"),
                Person(4, "Mikey", "Bailey")
        )
        SUT.insert(persons)
        return persons
    }
}