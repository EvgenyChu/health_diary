package ru.churkin.health_diary.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import ru.churkin.health_diary.helpers.formatDateForUser
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CustomDateAdapter {
    var dateFormat: DateFormat =
        SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa", Locale.ENGLISH)

    @ToJson
    @Synchronized
    fun dateToJson(d: Date?): String? {
        return d?.formatDateForUser()
    }

    @FromJson
    @Synchronized
    @Throws(ParseException::class)
    fun dateFromJson(s: String?): Date? {
        return s?.let {
            dateFormat.parse(it)
        }
    }
}