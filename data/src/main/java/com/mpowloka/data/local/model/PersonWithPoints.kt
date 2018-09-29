package com.mpowloka.data.local.model

data class PersonWithPoints(
        val localId: Long = 0,
        val firstName: String,
        val lastName: String,
        val pictureUrl: String = "",
        val deleted: Boolean = false,
        val points: Long = 0
)