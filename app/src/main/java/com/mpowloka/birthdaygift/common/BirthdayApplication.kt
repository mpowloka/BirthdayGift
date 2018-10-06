package com.mpowloka.birthdaygift.common

import android.app.Activity
import android.app.Application
import com.mpowloka.birthdaygift.common.di.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class BirthdayApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}