package com.mpowloka.birthdaygift.common.di

import com.mpowloka.birthdaygift.persondetails.PersonDetailsActivity
import com.mpowloka.birthdaygift.persondetails.PersonDetailsModule
import com.mpowloka.birthdaygift.persons.PersonsActivity
import com.mpowloka.birthdaygift.persons.PersonsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [PersonsModule::class])
    abstract fun personsActivity(): PersonsActivity

    @ContributesAndroidInjector(modules = [PersonDetailsModule::class])
    abstract fun personDetailsActivity(): PersonDetailsActivity

}
