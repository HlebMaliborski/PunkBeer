import Dependencies.koin

plugins {
  id(Plugins.ANDROID_APPLICATION)
  kotlin(Plugins.KOTLIN_ANDROID)
}

android {
  compileSdk = Versions.App.COMPILE_SDK

  defaultConfig {
    minSdk = Versions.App.MIN_SDK
    targetSdk = Versions.App.TARGET_SDK
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = Versions.Compose.CORE
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(project(":base"))
  implementation(project(":feature-beer"))

  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.Google.MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_UI)
  implementation(Dependencies.Compose.COMPOSE_MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_TOOLING)
  implementation(Dependencies.Compose.COMPOSE_ACTIVITY)

  koin()
  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
