package com.mpowloka.domain.persons

import com.mpowloka.data.local.repository.IncidentsRepository
import javax.inject.Inject

class GetIncidentsForLocalPersonIdUseCase @Inject constructor(
        private val incidentsRepository: IncidentsRepository
) {

    fun getLiveData(localPersonId: Long) = incidentsRepository.getAllIncidentsForLocalPersonIdLiveData(localPersonId)

}