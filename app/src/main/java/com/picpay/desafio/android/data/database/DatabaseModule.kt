package com.picpay.desafio.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.data.database.models.UserEntity
import org.koin.dsl.module

val databaseModule = module {
    single { createDatabase(get()) }
    single { get<AppDatabase>().userDao() }
}

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}


private fun createDatabase(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "application_database"
    ).build()
}