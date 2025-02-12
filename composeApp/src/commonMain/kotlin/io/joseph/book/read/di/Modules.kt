package io.joseph.book.read.di

import org.koin.core.module.Module

expect val platformModule: Module

fun allModules(): List<Module> = listOf(
    platformModule
)
