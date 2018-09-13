package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.mpowloka.data.local.database.entity.IncidentEntity.Companion.TABLE_NAME
import org.joda.time.DateTime

@Entity(tableName = TABLE_NAME)
data class IncidentEntity(
        @PrimaryKey(autoGenerate = true) val localId: Long = 0,
        val name: String,
        val description: String = "",
        val points: Int,
        val pictureUrl: String = "",
        val creationDate: DateTime = DateTime.now(),
        val deleted: Boolean = false
) {

    companion object {

        const val TABLE_NAME = "Incidents"

    }

}