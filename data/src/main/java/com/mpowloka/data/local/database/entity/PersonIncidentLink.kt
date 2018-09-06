package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index

@Entity(
        primaryKeys = [
            "localPersonId",
            "localIncidentId"
        ],
        indices = [
            Index(value = ["localPersonId"]),
            Index(value = ["localIncidentId"])
        ],
        foreignKeys = [
            ForeignKey(
                    entity = Incident::class,
                    parentColumns = ["localId"],
                    childColumns = ["localIncidentId"]
            ),
            ForeignKey(
                    entity = Person::class,
                    parentColumns = ["localId"],
                    childColumns = ["localPersonId"]
            )
        ]
)
data class PersonIncidentLink(
        val localPersonId: Long,
        val localIncidentId: Long
)