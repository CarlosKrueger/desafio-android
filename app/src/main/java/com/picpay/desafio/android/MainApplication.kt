package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.database.databaseModule
import com.picpay.desafio.android.data.repositories.repositoriesModule
import com.picpay.desafio.android.service.networkModule
import com.picpay.desafio.android.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(networkModule, databaseModule, repositoriesModule, viewModelModule)
            )
        }

    }
}