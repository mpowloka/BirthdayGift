package com.mpowloka.architecture.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : LiveData<T>() {

    private val eventPending = AtomicBoolean(true)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        super.observe(owner, Observer { data ->
            if (eventPending.compareAndSet(true, false)) {
                observer.onChanged(data)
            }
        })
    }

    @MainThread
    fun notifyHappened(data: T? = null) {
        eventPending.set(true)
        value = data
    }

}