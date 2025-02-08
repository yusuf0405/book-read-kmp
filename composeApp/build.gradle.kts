import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import extensions.with

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kmp.configuration)
}

val allModules = listOf(
    projects.core.common,
    projects.core.data,
    projects.core.domain,
    projects.core.database,
    projects.core.network,
    projects.core.ui,

    projects.features.favorites.favoritesApi,
    projects.features.favorites.favoritesImpl,
    projects.features.main.mainApi,
    projects.features.main.mainImpl,
    projects.features.search.searchApi,
    projects.features.search.searchImpl,
)

kotlin {
    jvmToolchain(17)
    androidTarget {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }

    jvm()
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

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
            allModules.forEach(::implementation)
            implementation(libs.desingnsystem)
        }
    }
}

android {
    namespace = "io.joseph.book.read"

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
    }
}

//https://developer.android.com/develop/ui/compose/testing#setup
dependencies {
    androidTestImplementation(libs.androidx.uitest.junit4)
    debugImplementation(libs.androidx.uitest.testManifest)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.versions.packageName.get()
            packageVersion = libs.versions.packageVersion.get()

            linux {
                iconFile.set(project.file("desktopAppIcons/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("desktopAppIcons/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("desktopAppIcons/MacosIcon.icns"))
                bundleID = "io.joseph.book.read.desktopApp"
            }
        }
    }
}