import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("maven-publish")
    id("com.google.dagger.hilt.android")
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

android {
    namespace = "com.eyedea.animax.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.eyedea.animax.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    repositories {
        google()
        mavenCentral()
        maven {
            name = "core_library"
            url = uri("https://maven.pkg.github.com/Deorigami/core_module") // Github Package
            credentials {
                //Fetch these details from the properties file or from Environment variables
                username = githubProperties["gpr.userId"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.token"] as String? ?: System.getenv("GPR_API_KEY")
            }
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.eyedea:core:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    api("androidx.fragment:fragment-ktx:1.5.5")
    val media3_version = "1.0.0-beta03"
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")
    implementation("androidx.media3:media3-session:$media3_version")
    implementation("androidx.media3:media3-exoplayer-dash:$media3_version")
    implementation("androidx.media3:media3-exoplayer-hls:$media3_version")
    implementation("androidx.core:core-ktx:1.9.0")
    val appcompat_version = "1.5.1"

    implementation("androidx.appcompat:appcompat:$appcompat_version")
    // For loading and tinting drawables on older versions of the platform
    implementation("androidx.appcompat:appcompat-resources:$appcompat_version")
    implementation("com.google.android.material:material:1.7.0")

    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")

    implementation("androidx.navigation:navigation-common-ktx:2.5.3")
    implementation("androidx.navigation:navigation-runtime:2.5.3")
    implementation("androidx.navigation:navigation-fragment:2.5.3")

}
