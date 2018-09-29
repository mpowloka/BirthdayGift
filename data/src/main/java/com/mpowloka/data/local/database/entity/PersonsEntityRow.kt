package com.mpowloka.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mpowloka.data.local.database.entity.PersonsEntityRow.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PersonsEntityRow(
        @PrimaryKey(autoGenerate = true) val localId: Long = 0,
        val firstName: String,
        val lastName: String,
        val pictureUrl: String = "",
        val deleted: Boolean = false
) {

    companion object {
        const val TABLE_NAME = "Persons"
    }
}