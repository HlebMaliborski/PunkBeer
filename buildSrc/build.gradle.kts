plugins {
  `kotlin-dsl`
}

repositories {
  mavenLocal()
  //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/tools/android/gradle-plugin/")
  //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/agent/agent-android/")
  google()
  mavenCentral()
}


dependencies {
  // This should be in-sync with the Gradle version exposed by `Versions.kt`
  implementation("com.android.tools.build:gradle:7.2.2")

  // This should be in-sync with the Kotlin version exposed by `Versions.kt`
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
  implementation("org.ow2.asm:asm-util:9.3")
  implementation(kotlin("stdlib"))
  gradleApi()
}