package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Incident(
        @PrimaryKey(autoGenerate = true) val localId: Int = 0,
        val name: String,
        val description: String = "",
        val points: Int
)