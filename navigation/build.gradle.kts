import Dependencies.compose

plugins {
  id(Plugins.COMMON)
  id("kotlin-android")
}

dependencies {
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.AndroidX.VIEW_MODEL)
  implementation(Dependencies.Google.MATERIAL)
  compose()

  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
