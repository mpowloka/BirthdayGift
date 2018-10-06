package com.mpowloka.data.local.database.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateTimeTypeConverter {

    @TypeConverter
    fun toDateTime(millis: Long) = DateTime(millis)

    @TypeConverter
    fun toLong(dateTime: DateTime) = dateTime.millis

}