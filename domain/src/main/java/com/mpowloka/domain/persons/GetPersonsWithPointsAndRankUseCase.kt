package com.mpowloka.domain.persons

import com.mpowloka.data.local.repository.PersonsRepository
import javax.inject.Inject

class GetPersonsWithPointsAndRankUseCase @Inject constructor(
        private val personsRepository: PersonsRepository
) {

    fun getLiveData() = personsRepository.getAllPersonsWithPointsAndRankLiveData()

}