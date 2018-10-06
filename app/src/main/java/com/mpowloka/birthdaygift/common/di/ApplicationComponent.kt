package com.mpowloka.birthdaygift.common.di

import android.app.Application
import com.mpowloka.birthdaygift.common.BirthdayApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidInjectionModule::class,
    ViewModelModule::class,
    AppModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

    fun inject(birthdayApplication: BirthdayApplication)

}