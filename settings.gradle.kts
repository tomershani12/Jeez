pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()     // Add Google Maven repository here
        mavenCentral()
    }
}

rootProject.name = "Jeez"
include(":app")
