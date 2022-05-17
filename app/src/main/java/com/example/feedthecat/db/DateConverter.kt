package com.example.feedthecat.db

import android.util.Log
import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.getDefault())

        if (value?.isNotEmpty() == true) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
                Log.d("APP-NFC-TO-SERVERC", "fromTimestamp ParseException e=$e")
            }
        }

        return null
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.getDefault())
        return if (value != null) df.format(value) else null
    }
}