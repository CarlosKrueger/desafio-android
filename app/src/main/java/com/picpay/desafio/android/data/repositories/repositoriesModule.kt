package com.picpay.desafio.android.data.repositories

import org.koin.dsl.module

val repositoriesModule = module {
    factory {
        UsersRepository(get(), get())
    }
}