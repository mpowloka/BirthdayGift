package com.mpowloka.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.mpowloka.data.local.database.converter.DateTimeTypeConverter
import com.mpowloka.data.local.database.dao.IncidentDao
import com.mpowloka.data.local.database.dao.PersonsDao
import com.mpowloka.data.local.database.dao.PersonIncidentLinkDao
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import com.mpowloka.data.local.database.entity.PersonIncidentLinkEntity

@Database(
        entities = [
            Person::class,
            Incident::class,
            PersonIncidentLinkEntity::class
        ],
        version = 1
)
@TypeConverters(DateTimeTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract val personsDao: PersonsDao

    abstract val incidentDao: IncidentDao

    abstract val personIncidentLinkDao: PersonIncidentLinkDao

    companion object {

        private const val DATABASE_NAME = "main_database"
        private var INSTANCE: MainDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MainDatabase {
            val result = INSTANCE
                    ?: Room.databaseBuilder(context, MainDatabase::class.java, DATABASE_NAME).build()
            INSTANCE = result
            return result
        }

    }

}