package com.mpowloka.birthdaygift.persons

import androidx.lifecycle.ViewModel
import com.mpowloka.architecture.util.SingleLiveEvent
import com.mpowloka.data.local.model.PersonWithPointsAndRank
import com.mpowloka.domain.persons.GetPersonsWithPointsAndRankUseCase
import javax.inject.Inject

class PersonsViewModel @Inject constructor(
        getPersonsWithPointsAndRankUseCase: GetPersonsWithPointsAndRankUseCase
): ViewModel() {

    val personsWithPointsAndRank = getPersonsWithPointsAndRankUseCase.getLiveData()

    val personCardsClicks = SingleLiveEvent<PersonWithPointsAndRank>()

}