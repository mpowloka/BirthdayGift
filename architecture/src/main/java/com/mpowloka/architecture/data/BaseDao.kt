package com.mpowloka.architecture.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>): List<Long>

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