package com.mpowloka.birthdaygift.common.di

import android.app.Application
import com.mpowloka.birthdaygift.common.BirthdayApplication
import com.mpowloka.data.local.di.DatabaseModule
import com.mpowloka.data.local.di.RepositoriesModule
import com.mpowloka.domain.di.ExecutorsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidInjectionModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    ExecutorsModule::class,
    RepositoriesModule::class,
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