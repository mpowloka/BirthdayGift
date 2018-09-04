package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity

@Entity(
        primaryKeys = [
            "localPersonId",
            "localIncidentId"
        ]
)
data class PersonIncidentAsoc(
        val localPersonId: Int,
        val localIncidentId: Int
)