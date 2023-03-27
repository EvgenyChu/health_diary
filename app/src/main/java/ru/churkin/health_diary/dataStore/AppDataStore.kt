package ru.churkin.health_diary.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.churkin.health_diary.helpers.parseObj
import ru.churkin.health_diary.helpers.toJson
import ru.churkin.health_diary.modelData.VersionApp
import javax.inject.Inject
import javax.inject.Singleton

interface IPrefsStore {
    fun getVersionApp(): VersionApp?
    fun getVersionAppFlow(): Flow<VersionApp?>
    suspend fun editVersionApp(versionApp: VersionApp)
    suspend fun removeVersionApp()
}

@Singleton
class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    val moshi: Moshi
) : IPrefsStore {

    private object PreferencesKeys {
        val VERSION_APP = stringPreferencesKey("version_app")
    }

    private var _versionApp: VersionApp? = null

    init {
        CoroutineScope(SupervisorJob()).launch {
            getVersionAppFlow().collect { _versionApp = it }
        }
    }

    override fun getVersionApp(): VersionApp? = _versionApp

    override fun getVersionAppFlow(): Flow<VersionApp?> = dataStore.data.map { pref ->
        moshi.parseObj(pref[PreferencesKeys.VERSION_APP])
    }

    override suspend fun editVersionApp(versionApp: VersionApp) {
        dataStore.edit { pref ->
            pref[PreferencesKeys.VERSION_APP] = moshi.toJson(versionApp)
        }
    }

    override suspend fun removeVersionApp() {
        dataStore.edit { preferences -> preferences.remove(PreferencesKeys.VERSION_APP) }
    }
}