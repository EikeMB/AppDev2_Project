

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "0.34.0"
}


repositories {
  // Required to download KtLint
  mavenCentral()
}
