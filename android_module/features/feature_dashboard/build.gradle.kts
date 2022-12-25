apply {
    from("$rootDir/android_feature_build.gradle")
}

dependencies {
    "api"(project(":android_module:features:feature_config"))
}