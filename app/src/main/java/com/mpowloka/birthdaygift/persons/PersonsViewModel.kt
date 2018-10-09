package com.mpowloka.birthdaygift.persons

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.persons.GetPersonsWithPointsUseCase
import javax.inject.Inject

class PersonsViewModel @Inject constructor(
    getPersonsWithPointsUseCase: GetPersonsWithPointsUseCase
): ViewModel() {

    val personsWithPoints = getPersonsWithPointsUseCase.getLiveData()

}