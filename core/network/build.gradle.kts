import extensions.with

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kmp.configuration)
}

kotlin {
    with(
        configuration = kmpConfiguration,
        extension = this
    ) { configuration, extension ->
        configuration.apply {
            coroutine.configureDependencies(extension)
            koin.configureDependencies(extension)
            ktor.configureDependencies(extension)
        }
    }
}

android {
    namespace = "io.joseph.book.read.network"
}