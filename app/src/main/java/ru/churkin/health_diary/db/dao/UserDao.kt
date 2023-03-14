package ru.churkin.health_diary.db.dao

import androidx.room.*
import ru.churkin.health_diary.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(obj:UserEntity):Long

    @Delete
    fun delete(user: UserEntity)

    @Query(""" SELECT * FROM users """)
    suspend fun getAll(): List<UserEntity>

    @Query(
        """ 
        SELECT * FROM users 
        WHERE isActive = 1
        """
    )
    suspend fun getActiveUser(): UserEntity?

    @Query(""" DELETE FROM users WHERE userId = :id """)
    suspend fun delete(id: Int)
}