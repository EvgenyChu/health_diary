package ru.churkin.health_diary.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.churkin.health_diary.db.AppDb
import ru.churkin.health_diary.db.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun appDb(@ApplicationContext context: Context): AppDb = Room.databaseBuilder(
        context,
        AppDb::class.java,
        AppDb.DATABASE_NAME
    ).fallbackToDestructiveMigrationOnDowngrade().build()

    @Provides
    @Singleton
    fun userDao(db: AppDb): UserDao = db.userDao()
}