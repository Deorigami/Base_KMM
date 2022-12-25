pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OpenTrip"
include(":android_module:androidApp")
include(":shared")
include(":android_module:ui_component")
include(":android_module:ui_gallery")

/**
 * Module Features
 * */

include(":android_module:features:feature_dashboard")
