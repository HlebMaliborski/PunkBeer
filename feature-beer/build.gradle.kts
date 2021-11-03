plugins {
  id(Plugins.COMMON)
  id("kotlin-android")
}

dependencies {
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.Google.MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_UI)
  implementation(Dependencies.Compose.COMPOSE_MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_TOOLING)
  implementation(Dependencies.Compose.COMPOSE_ACTIVITY)
  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
