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
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)
            implementation(projects.core.network)
        }
    }
}

android {
    namespace = "io.joseph.book.read.data"
}