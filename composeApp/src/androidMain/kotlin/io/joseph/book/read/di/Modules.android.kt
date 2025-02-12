package io.joseph.book.read.di

import io.joseph.book.read.common.PlatformConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

actual val platformModule = module {
    single { PlatformConfiguration(androidApplication()) }
}