package ru.churkin.health_diary

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ru.churkin.health_diary.dataStore.AppDataStore
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    companion object {
        private lateinit var instance: App
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}