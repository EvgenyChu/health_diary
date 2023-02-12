package ru.churkin.health_diary.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.churkin.health_diary.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insertAll(users: List<UserEntity>)

    @Delete
    fun delete(user: UserEntity)

    @Query(""" SELECT * FROM users """)
    fun getAll(): List<UserEntity>

    @Query(""" DELETE FROM users WHERE userId = :id """)
    suspend fun delete(id: Int)
}