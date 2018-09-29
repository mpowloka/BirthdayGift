package com.mpowloka.data.local.model

data class PersonWithPointsAndRank(
        val personWithPoints: PersonWithPoints,
        val rank: Int = -1
)