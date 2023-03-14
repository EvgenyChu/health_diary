package ru.churkin.health_diary.db

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import ru.churkin.health_diary.BuildConfig
import ru.churkin.health_diary.db.dao.UserDao
import ru.churkin.health_diary.db.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = AppDb.DATABASE_VERSION,
    exportSchema = true,
)

@TypeConverters(
    DateConverter::class,
)

abstract class AppDb : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = BuildConfig.APPLICATION_ID + ".db"
        const val DATABASE_VERSION = 1
    }

    abstract fun userDao(): UserDao
}