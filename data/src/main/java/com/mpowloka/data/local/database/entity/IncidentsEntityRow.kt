package com.mpowloka.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mpowloka.data.local.database.entity.IncidentsEntityRow.Companion.TABLE_NAME
import org.joda.time.DateTime

@Entity(tableName = TABLE_NAME)
data class IncidentsEntityRow(
        @PrimaryKey(autoGenerate = true) val localId: Long = 0,
        val name: String,
        val description: String = "",
        val points: Long,
        val pictureUrl: String = "",
        val creationDate: DateTime = DateTime.now(),
        val deleted: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "Incidents"
    }
}