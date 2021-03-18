package com.picpay.desafio.android.service

import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { createRetrofit() }
    single { createService(get()) }
}

private const val url = "http://careers.picpay.com/tests/mobdev/"

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(
            OkHttpClient.Builder().build()
        )
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().create())
        )
        .build()
}

private fun createService(retrofit: Retrofit): PicPayService {
    return retrofit.create(PicPayService::class.java)
}