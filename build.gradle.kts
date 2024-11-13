buildscript {
    repositories {
        google() // This is necessary for Google dependencies
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
}

allprojects {
    repositories {
            google() // Ensure the Google repository is here too
        mavenCentral()
    }
}
