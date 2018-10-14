package com.mpowloka.birthdaygift.persondetails

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.persons.GetIncidentsForLocalPersonIdUseCase
import com.mpowloka.domain.persons.GetPersonWithPointsAndRankForLocalIdUseCase

class PersonDetailsViewModel constructor(
        private val getPersonWithPointsAndRankForLocalIdUseCase: GetPersonWithPointsAndRankForLocalIdUseCase,
        private val getIncidentsForLocalPersonIdUseCase: GetIncidentsForLocalPersonIdUseCase
) : ViewModel() {

    fun getPersonWithPointsAndRank(localPersonId: Long) = getPersonWithPointsAndRankForLocalIdUseCase.getLiveData(localPersonId)

    fun getPersonIncidents(localPersonId: Long) = getIncidentsForLocalPersonIdUseCase.getLiveData(localPersonId)

}