package com.mpowloka.birthdaygift.common.di

import com.mpowloka.birthdaygift.persons.PersonsActivity
import com.mpowloka.birthdaygift.persons.PersonsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [PersonsModule::class])
    abstract fun personsActivity(): PersonsActivity

}
