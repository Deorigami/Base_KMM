apply {
    from("$rootDir/android_feature_build.gradle")
}

dependencies {
    "implementation"("com.github.bumptech.glide:glide:4.14.2")
    "api"(project(":android_module:ui_component"))
}