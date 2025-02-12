import extensions.with

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kmp.configuration)
}

kotlin {
    room {
        schemaDirectory("$projectDir/schemas")
    }
    with(
        configuration = kmpConfiguration,
        extension = this
    ) { configuration, extension ->
        configuration.apply {
            coroutine.configureDependencies(extension)
            koin.configureDependencies(extension)
            room.configureDependencies(extension)
            kotlinX.configureDependencies(extension)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
        }

        dependencies {
            ksp(kmpConfiguration.room.roomCompiler)
        }
    }
}

android {
    namespace = "io.joseph.book.read.database"
}