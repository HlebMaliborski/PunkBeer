plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = Versions.App.COMPILE_SDK

  defaultConfig {
    applicationId = "com.devloper.squad.punkbeer"
    minSdk = Versions.App.MIN_SDK
    targetSdk = Versions.App.TARGET_SDK
    versionCode = Versions.App.VERSION_CODE
    versionName = Versions.App.VERSION_NAME

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
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
  implementation(Dependencies.AndroidX.CORE)
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.Google.MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_UI)
  implementation(Dependencies.Compose.COMPOSE_MATERIAL)
  implementation(Dependencies.Compose.COMPOSE_TOOLING)
  implementation(Dependencies.Compose.COMPOSE_ACTIVITY)

  testImplementation(Dependencies.Test.Unit.JUNIT)
  androidTestImplementation(Dependencies.Test.Integration.JUNIT)
  androidTestImplementation(Dependencies.Test.Integration.ESPRESSO_CORE)
  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
