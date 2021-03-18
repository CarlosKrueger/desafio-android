package com.picpay.desafio.android.service

import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<PicPayServiceUser>
}
