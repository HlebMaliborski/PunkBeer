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

  koin()
  retrofit()
  compose()
  implementation("io.coil-kt:coil-compose:1.4.0")
  implementation("com.google.accompanist:accompanist-swiperefresh:0.24.3-alpha")
  implementation ("com.google.accompanist:accompanist-pager:0.23.1")

  // If using indicators, also depend on
  implementation ("com.google.accompanist:accompanist-pager-indicators:0.23.1")
  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
