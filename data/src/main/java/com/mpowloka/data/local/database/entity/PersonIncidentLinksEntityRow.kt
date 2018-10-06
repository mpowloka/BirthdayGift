package com.mpowloka.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow.Companion.TABLE_NAME

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
                    entity = IncidentsEntityRow::class,
                    parentColumns = ["localId"],
                    childColumns = ["localIncidentId"]
            ),
            ForeignKey(
                    entity = PersonsEntityRow::class,
                    parentColumns = ["localId"],
                    childColumns = ["localPersonId"]
            )
        ]
)
internal data class PersonIncidentLinksEntityRow(
        val localPersonId: Long,
        val localIncidentId: Long
) {

    companion object {
        const val TABLE_NAME = "PersonIncidentLinks"
    }
}