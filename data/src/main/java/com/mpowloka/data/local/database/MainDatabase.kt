package com.mpowloka.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.mpowloka.data.local.database.converter.DateTimeTypeConverter
import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.dao.PersonIncidentLinkDao
import com.mpowloka.data.local.database.entity.IncidentsEntityRow
import com.mpowloka.data.local.database.entity.PersonsEntityRow
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow

@Database(
        entities = [
            PersonsEntityRow::class,
            IncidentsEntityRow::class,
            PersonIncidentLinksEntityRow::class
        ],
        version = 1
)
@TypeConverters(DateTimeTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract val personsDao: PersonsDao

    abstract val incidentDao: IncidentDao

    abstract val personIncidentLinkDao: PersonIncidentLinkDao

    companion object {
        const val DATABASE_NAME = "main_database"
    }
}