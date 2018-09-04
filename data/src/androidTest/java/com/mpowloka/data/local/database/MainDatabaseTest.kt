package com.mpowloka.data.local.database

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainDatabaseTest {

    @Test
    fun getInstance_calledMultipleTimes_sameInstanceReturned() {
        val instance = MainDatabase.getInstance(InstrumentationRegistry.getContext())

        assertTrue(instance === MainDatabase.getInstance(InstrumentationRegistry.getContext()))
        assertTrue(instance === MainDatabase.getInstance(InstrumentationRegistry.getContext()))
        assertTrue(instance === MainDatabase.getInstance(InstrumentationRegistry.getContext()))
    }
}