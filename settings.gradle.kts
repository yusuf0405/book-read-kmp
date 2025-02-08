rootProject.name = "book-read-kmp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
includeSubprojects()

fun includeSubprojects() {
    includeModulesIn("features", depth = 2)
    includeModulesIn("core", depth = 2)
}

fun includeModulesIn(directoryName: String, depth: Int) {
    initModules(File(rootDir, directoryName), depth)
}

fun initModules(sourceDir: File, depth: Int, moduleBlackList: List<String> = emptyList()) {
    sourceDir.walk()
        .maxDepth(depth)
        .filter { it.shouldIncludeModule(sourceDir, moduleBlackList) }
        .forEach { moduleDir ->
            val moduleName = moduleDir.toModuleName()
            include(moduleName)
            project(moduleName).projectDir = moduleDir
        }
}

fun File.shouldIncludeModule(sourceDir: File, moduleBlackList: List<String>): Boolean {
    return name !in moduleBlackList &&
            isDirectory &&
            this != sourceDir &&
            isGradleProject()
}

fun File.toModuleName(): String {
    return path.substringAfter(rootDir.name).replace(File.separatorChar, ':')
}

fun File.isGradleProject(): Boolean {
    return isDirectory && listOf("build.gradle.kts", "build.gradle").any {
        File(this, it).exists()
    }
}