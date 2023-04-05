import Dependencies.compose
import Dependencies.koin
import Dependencies.retrofit

plugins {
  id(Plugins.COMMON)
  id("kotlin-android")
}

dependencies {
  implementation(project(":base"))
  implementation(project(":navigation"))

  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.AndroidX.VIEW_MODEL)
  implementation(Dependencies.Google.MATERIAL)
  implementation("androidx.compose.material:material:1.4.0")

  koin()
  retrofit()
  compose()
  implementation("io.coil-kt:coil-compose:1.4.0")
  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
