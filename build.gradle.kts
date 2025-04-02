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
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt.gralde.plugin) apply false
    alias(libs.plugins.kotlin.ksp) apply false
}
true // Needed to make the Suppress annotation work for the plugins block