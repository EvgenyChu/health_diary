package ru.churkin.health_diary.repositories

import ru.churkin.health_diary.db.dao.UserDao
import ru.churkin.health_diary.db.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

interface IUserRepository{
suspend fun getActiveUser() : UserEntity
suspend fun getAllUser() : List<UserEntity>
}

@Singleton
class UserRepository @Inject constructor (
    private val userDao: UserDao
) : IUserRepository{
    override suspend fun getActiveUser() = userDao.getActiveUser()

    override suspend fun getAllUser() = userDao.getAll()
}