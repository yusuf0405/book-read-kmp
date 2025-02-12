package io.joseph.book.read.di

import io.joseph.book.read.database.di.databaseModule
import org.koin.core.module.Module

expect val platformModule: Module

fun allModules(): List<Module> = listOf(
    platformModule,
    databaseModule
)