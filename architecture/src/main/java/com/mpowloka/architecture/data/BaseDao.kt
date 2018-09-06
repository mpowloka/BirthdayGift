package com.mpowloka.architecture.data

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg items: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(items: List<T>)

    @Delete
    fun delete(item: T)

    @Delete
    fun delete(vararg items: T)

    @Delete
    fun delete(items: List<T>)

}