import Dependencies.common
import Dependencies.koin
import Dependencies.commonIntegrationTest

plugins {
  id("com.android.library")
  kotlin("android")
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
    kotlinCompilerExtensionVersion = "1.4.0"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  koin()
  common()
  commonIntegrationTest()
}
