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
            lifecycle.configureDependencies(extension)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.desingnsystem)
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

android {
    namespace = "io.joseph.book.read.ui"
}