package ru.churkin.health_diary.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val userId: Int,
    val name: String,
    val age: Int,
    val weight: Int,
    @ColumnInfo(defaultValue = "0")
    val isActive: Boolean,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Date = Date(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    var updatedAt: Date = Date(),
)