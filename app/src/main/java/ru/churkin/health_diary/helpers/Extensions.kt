package ru.churkin.health_diary.helpers

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).parse(this)
fun Date.formatDateForUser(): String = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT).format(this)
fun Date.formatDateWithoutTime(): String = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(this)

inline fun <reified T> Moshi.parseList(jsonString: String?): List<T> {
    return jsonString?.let {
        adapter<List<T>>(
            Types.newParameterizedType(
                List::class.java,
                T::class.java
            )
        ).fromJson(it)
    } ?: emptyList()
}

inline fun <reified T> Moshi.parseObj(jsonString: String?): T? {
    return jsonString?.let { adapter(T::class.java).fromJson(jsonString) }
}

inline fun <reified T> Moshi.toJson(list: List<T>): String {
    return adapter<List<T>>(
        Types.newParameterizedType(
            List::class.java,
            T::class.java
        )
    ).toJson(list)
}

inline fun <reified T> Moshi.toJson(obj: T): String {
    return adapter(T::class.java).toJson(obj)
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }