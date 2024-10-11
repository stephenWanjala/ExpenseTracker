// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {

        classpath(libs.hilt.android.gradle.plugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false
    alias(libs.plugins.compose.compiler) apply false
}
true // Needed to make the Suppress annotation work for the plugins block