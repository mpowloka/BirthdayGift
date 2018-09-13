package com.mpowloka.data.local.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.mpowloka.data.local.database.entity.PersonEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PersonEntity(
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