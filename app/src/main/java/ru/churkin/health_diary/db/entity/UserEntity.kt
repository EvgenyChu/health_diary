package ru.churkin.health_diary.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    val userId: Int,
    val name: String,
    val age: Int,
    val weight: Int
        )