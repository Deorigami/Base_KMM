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

rootProject.name = "Base KMM Project"
include(":android_module:app")
include(":shared")
include(":android_module:ui_component")
include(":android_module:ui_gallery")

/**
 * Module Features
 * */

include(":android_module:features:feature_dashboard")
include(":android_module:features:feature_config")
include(":shared_module:shared_core")
