package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.service.PicPayService
import androidx.lifecycle.Transformations
import com.picpay.desafio.android.data.domain.models.User
import androidx.lifecycle.LiveData

class UsersRepository(
    private val service: PicPayService,
    private val userDao: UserDao
) {

    val users: LiveData<List<User>> = Transformations.map(userDao.getAll()) { it.toUsers() }

    @Throws(NullPointerException::class)
    suspend fun fetchUsers() {
        val fetchedUsers = service.getUsers()
        userDao.insertAll(fetchedUsers.toUserEntity())
    }
}


