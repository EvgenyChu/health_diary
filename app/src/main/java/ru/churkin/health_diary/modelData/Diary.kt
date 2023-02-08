package ru.churkin.health_diary.modelData

import java.util.Date

data class Diary (
    val id: Long,
    val date: Date,
    val topPressure: Int,
    val bottomPressure: Int,
    val pulse: Int
        )