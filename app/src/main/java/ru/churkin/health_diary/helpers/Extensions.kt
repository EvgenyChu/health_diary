package ru.churkin.health_diary.helpers

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDateForUser(): String = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT).format(this)