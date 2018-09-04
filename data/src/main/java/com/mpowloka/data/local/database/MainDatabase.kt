package com.mpowloka.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mpowloka.data.local.database.entity.Incident
import com.mpowloka.data.local.database.entity.Person
import com.mpowloka.data.local.database.entity.PersonIncidentAsoc

@Database(
        entities = [
            Person::class,
            Incident::class,
            PersonIncidentAsoc::class
        ],
        version = 1
)
abstract class MainDatabase : RoomDatabase() {

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