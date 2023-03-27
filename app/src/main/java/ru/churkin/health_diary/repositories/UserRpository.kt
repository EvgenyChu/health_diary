package ru.churkin.health_diary.repositories

import android.content.Context
import android.content.pm.PackageManager
import kotlinx.coroutines.flow.firstOrNull
import ru.churkin.health_diary.App
import ru.churkin.health_diary.BuildConfig
import ru.churkin.health_diary.dataStore.AppDataStore
import ru.churkin.health_diary.db.dao.UserDao
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.helpers.getPackageInfoCompat
import ru.churkin.health_diary.modelData.VersionApp
import javax.inject.Inject
import javax.inject.Singleton

interface IUserRepository{
suspend fun getActiveUser() : UserEntity?
suspend fun getAllUsers() : List<UserEntity>
suspend fun addUser(user: UserEntity): Long
suspend fun saveVersionAppAfterUpdate()
}

@Singleton
class UserRepository @Inject constructor (
    private val userDao: UserDao,
    private val dataStore: AppDataStore
) : IUserRepository{
    override suspend fun getActiveUser() = userDao.getActiveUser()

    override suspend fun getAllUsers() = userDao.getAll()

    override suspend fun addUser(user: UserEntity) = userDao.insertUser(user)

    override suspend fun saveVersionAppAfterUpdate() {
        val versionSaveLast = dataStore.getVersionAppFlow().firstOrNull()
        val thisAppVersion =
            VersionApp(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE.toString())

        when {
            isFirstInstall(App.applicationContext())
                    || versionSaveLast == null
                    || (versionSaveLast.versionCode != thisAppVersion.versionCode) -> {
                dataStore.editVersionApp(thisAppVersion)
            }

            else -> { }
        }
    }

    private fun isFirstInstall(context: Context): Boolean {
        return try {
            val firstInstallTime = context.packageManager
                .getPackageInfoCompat(context.packageName).firstInstallTime
            val lastUpdateTime =context.packageManager
                .getPackageInfoCompat(context.packageName).lastUpdateTime
            firstInstallTime == lastUpdateTime
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            true
        }
    }
}