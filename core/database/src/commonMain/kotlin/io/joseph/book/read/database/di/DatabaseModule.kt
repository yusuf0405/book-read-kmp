package io.joseph.book.read.database.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.joseph.book.read.database.DatabaseFactory
import io.joseph.book.read.database.FavoriteBookDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseFactory(get()) }
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }
}