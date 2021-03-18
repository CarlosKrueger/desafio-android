package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.service.PicPayServiceUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Test

class PicPayServiceTest {

    private val api = mock<PicPayService>()

    @Test
    @ExperimentalCoroutinesApi
    fun serviceTest() {
        val testScope = TestCoroutineScope()
        val expectedUsers = emptyList<PicPayServiceUser>()

        testScope.launch {
            val users = api.getUsers()
            assertEquals(users, expectedUsers)
        }
    }
}