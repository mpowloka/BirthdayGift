package com.mpowloka.data.local.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import java.util.*

class TestDataPreloadCallback : RoomDatabase.Callback() {

    private val random = Random()

    override fun onCreate(db: SupportSQLiteDatabase) {

        for (i in (0 until PERSONS_COUNT)) {
            db.insert(
                    PersonsEntityRow.TABLE_NAME,
                    SQLiteDatabase.CONFLICT_REPLACE,
                    generatePersonContentValues()
            )
        }

        for (i in (0 until INCIDENTS_COUNT)) {
            db.insert(
                    IncidentsEntityRow.TABLE_NAME,
                    SQLiteDatabase.CONFLICT_REPLACE,
                    generateIncidentContentValues()
            )
        }

        for (i in (0 until LINKS_COUNT)) {
            db.insert(
                    PersonIncidentLinksEntityRow.TABLE_NAME,
                    SQLiteDatabase.CONFLICT_REPLACE,
                    generateLinkContentValues()
            )
        }


    }

    private fun generateLinkContentValues(): ContentValues {
        return ContentValues().apply {
            put("localPersonId", random.nextInt(PERSONS_COUNT).toLong())
            put("localIncidentId", random.nextInt(INCIDENTS_COUNT).toLong())
        }
    }

    private fun generateIncidentContentValues(): ContentValues {
        return ContentValues().apply {
            put("name", incidentNamesPool[random.nextInt(incidentNamesPool.size)])
            put("description", "I am too lazy for that, sorry")
            put("points", random.nextInt(101).toLong())
            put("pictureUrl", urlsPool[random.nextInt(urlsPool.size)])
            put("creationDate", 1000L)
            put("deleted", false)
        }
    }

    private fun generatePersonContentValues(): ContentValues {
        return ContentValues().apply {
            put("firstName", firstNamesPool[random.nextInt(firstNamesPool.size)])
            put("lastName", lastNamesPool[random.nextInt(lastNamesPool.size)])
            put("pictureUrl", urlsPool[random.nextInt(urlsPool.size)])
            put("deleted", false)
        }
    }

    private val firstNamesPool = listOf(
            "Michael",
            "Isabelle",
            "Barry",
            "Norman",
            "Louis",
            "Andy",
            "Clara"
    )

    private val lastNamesPool = listOf(
            "Stark",
            "Hawking",
            "Pink",
            "Cashback",
            "Lemon",
            "Moon",
            "Stevenson"
    )

    private val incidentNamesPool = listOf(
            "Fart",
            "Back flip",
            "Karaoke",
            "Ate tomato in elevator",
            "Fell down the stairs",
            "DEATHMACHINE"
    )

    private val urlsPool = listOf(
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/29694412_1763266137065080_1295364274947452659_n.jpg?_nc_cat=108&oh=fffd1b4b2eabba659235e78f80c2f627&oe=5C5E23B1",
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/23561518_1619681988090163_8605998578701905569_n.jpg?_nc_cat=108&oh=991fcb6d604cef9606eb421763860e9f&oe=5C1F3615",
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/14606304_1220732404651792_3983568287144386589_n.jpg?_nc_cat=106&oh=ec9f3ce3dde36c534e1556633218f77b&oe=5C185872",
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/13645297_1156900441034989_8322280682234218671_n.jpg?_nc_cat=108&oh=bac8173129697bea20c4fbafb87ea837&oe=5C165350",
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/12187711_997930136932021_436079080320861853_n.jpg?_nc_cat=107&oh=cb26e9e9748d9dc4a063bcc026911389&oe=5C49480F",
            "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/11060905_973427579382277_8064410404039766517_n.jpg?_nc_cat=105&oh=f1a22a107a74dbbbb3482ff65ff01cd1&oe=5C1A0879"

    )

    companion object {

        private const val PERSONS_COUNT = 15
        private const val INCIDENTS_COUNT = 40
        private const val LINKS_COUNT = 50

    }

}