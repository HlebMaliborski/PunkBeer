plugins {
  id(Plugins.COMMON)
  id("kotlin-android")
}

dependencies {
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.AndroidX.VIEW_MODEL)
  implementation(Dependencies.Google.MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_UI)
  implementation(Dependencies.Compose.COMPOSE_MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_TOOLING)
  implementation(Dependencies.Compose.COMPOSE_ACTIVITY)

  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
