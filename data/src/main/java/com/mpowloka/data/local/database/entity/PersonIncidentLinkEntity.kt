package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import com.mpowloka.data.local.database.entity.PersonIncidentLinkEntity.Companion.TABLE_NAME

@Entity(
        tableName = TABLE_NAME,
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
                    entity = IncidentEntity::class,
                    parentColumns = ["localId"],
                    childColumns = ["localIncidentId"]
            ),
            ForeignKey(
                    entity = PersonEntity::class,
                    parentColumns = ["localId"],
                    childColumns = ["localPersonId"]
            )
        ]
)
data class PersonIncidentLinkEntity(
        val localPersonId: Long,
        val localIncidentId: Long
) {

    companion object {

        const val TABLE_NAME = "PersonIncidentLinks"

    }

}