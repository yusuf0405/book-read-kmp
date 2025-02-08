import extensions.with

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kmp.configuration)
}

kotlin {
    with(
        configuration = kmpConfiguration,
        extension = this,
        composeDependencies = compose
    ) { configuration, extension, composeDependencies ->
        configuration.apply {
            compose.configureDependencies(extension, composeDependencies)
            coroutine.configureDependencies(extension)
            lifecycle.configureDependencies(extension)
            koin.configureDependencies(extension)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.domain)
            implementation(projects.features.main.mainApi)

            implementation(libs.desingnsystem)
        }
    }
}

android {
    namespace = "io.joseph.book.read.main"
}