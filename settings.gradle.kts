pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CodeLibs"
include(":app")
include(":core:domain")
project(":core:domain").projectDir = file("core_domain")
include(":core:data")
project(":core:data").projectDir = file("core_data")
include(":core:ui")
project(":core:ui").projectDir = file("core_ui")
include(":core:network")
project(":core:network").projectDir = file("core_network")
include(":feature:catalog")
project(":feature:catalog").projectDir = file("feature_catalog")
include(":feature:bookpage")
project(":feature:bookpage").projectDir = file("feature_bookpage")