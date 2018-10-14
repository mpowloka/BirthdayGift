package com.mpowloka.data.local.model

import org.joda.time.DateTime

data class Incident(
        val localId: Long = 0,
        val name: String,
        val description: String = "",
        val points: Long,
        val pictureUrl: String = "",
        val creationDate: DateTime = DateTime.now()
)