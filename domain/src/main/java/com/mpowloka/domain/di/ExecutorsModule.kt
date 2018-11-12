package com.mpowloka.domain.di

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class ExecutorsModule {

    @Provides
    @Named(MAIN_THREAD_EXECUTOR_NAME)
    @Singleton
    fun provideMainThreadExecutor(): Executor {
        return object : Executor {
            private val mainThreadHandler = Handler(Looper.getMainLooper())
            override fun execute(runnable: Runnable?) {
                mainThreadHandler.post(runnable)
            }
        }
    }

    @Provides
    @Named(DISC_EXECUTOR_NAME)
    @Singleton
    fun provideDiscExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Named(NETWORK_EXECUTOR_NAME)
    @Singleton
    fun provideNetworkExecutor(): Executor {
        return Executors.newFixedThreadPool(4)
    }

    companion object {

        const val MAIN_THREAD_EXECUTOR_NAME = "MAIN_THREAD_EXECUTOR_NAME"
        const val DISC_EXECUTOR_NAME = "DISC_EXECUTOR_NAME"
        const val NETWORK_EXECUTOR_NAME = "NETWORK_EXECUTOR_NAME"

    }

}