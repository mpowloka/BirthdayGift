package com.mpowloka.birthdaygift.common.di

import androidx.lifecycle.ViewModel
import com.mpowloka.architecture.viewmodel.ViewModelFactory
import com.mpowloka.birthdaygift.persons.PersonsViewModel
import com.mpowloka.domain.persons.GetPersonsWithPointsUseCase
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(
            providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PersonsViewModel::class)
    fun personsViewModel(
           getPersonsWithPointsUseCase: GetPersonsWithPointsUseCase
    ): ViewModel {
        return PersonsViewModel(getPersonsWithPointsUseCase)
    }

}