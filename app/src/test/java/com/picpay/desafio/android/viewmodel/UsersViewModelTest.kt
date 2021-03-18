package com.picpay.desafio.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.data.repositories.UsersRepository
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UsersViewModelTest {
    private val usersRepository = mock<UsersRepository>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchDataTest() {
        val usersViewModel = UsersViewModel(usersRepository)

        val observer = object : Observer<Int> {
            override fun onChanged(errorId: Int?) {
                Assert.assertEquals(errorId, null)
                usersViewModel.error.removeObserver(this)
            }
        }
        usersViewModel.error.observeForever(observer)
    }
}