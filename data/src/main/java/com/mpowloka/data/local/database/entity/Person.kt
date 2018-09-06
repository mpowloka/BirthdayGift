package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person(
        @PrimaryKey(autoGenerate = true) val localId: Long = 0,
        val firstName: String,
        val lastName: String
)