package com.mpowloka.domain.persons

import com.mpowloka.data.local.repository.PersonsRepository
import javax.inject.Inject

class GetPersonWithPointsAndRankForLocalIdUseCase @Inject constructor(
        private val personsRepository: PersonsRepository
) {

    fun getLiveData(localPersonId: Long) = personsRepository.getPersonWithPointsAndRankForLocalIdLiveData(localPersonId)

}