plugins {
  `kotlin-dsl`
}

repositories {
  google()
  mavenCentral()
}


dependencies {
  // This should be in-sync with the Gradle version exposed by `Versions.kt`
  implementation("com.android.tools.build:gradle:7.2.2")

  // This should be in-sync with the Kotlin version exposed by `Versions.kt`
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
}