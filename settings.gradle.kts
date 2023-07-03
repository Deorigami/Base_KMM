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

rootProject.name = "Auto Clicker"
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
include(":shared_module:shared_sample")
