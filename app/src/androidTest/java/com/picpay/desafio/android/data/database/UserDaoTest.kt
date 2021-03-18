package com.picpay.desafio.android.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.data.database.models.UserEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class UserDaoTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user = UserEntity(
            id = 102,
            img = "https://randomuser.me/api/portraits/men/9.jpg",
            name = "Carlos",
            username = "carloskrueger"
        )

        userDao.insertAll(listOf(user))

        val usersLiveData = userDao.getAll()
        val observer = object : Observer<List<UserEntity>> {
            override fun onChanged(userList: List<UserEntity>?) {
                assertThat(userList?.get(0), equalTo(user))
                usersLiveData.removeObserver(this)
            }
        }
        usersLiveData.observeForever(observer)
    }
}
