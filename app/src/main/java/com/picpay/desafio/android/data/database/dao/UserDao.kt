package com.picpay.desafio.android.data.database.dao

import com.picpay.desafio.android.data.database.models.UserEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Delete
    fun delete(user: UserEntity)
}