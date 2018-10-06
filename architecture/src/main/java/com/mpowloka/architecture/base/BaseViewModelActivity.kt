@file:Suppress("DEPRECATION")

package com.mpowloka.architecture.base

import android.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.mpowloka.architecture.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

abstract class BaseViewModelActivity<T : ViewModel> : AppCompatActivity(), HasFragmentInjector {

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected abstract fun getViewModelClass(): Class<T>

    val viewModel: T by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun fragmentInjector() = frameworkFragmentInjector

}