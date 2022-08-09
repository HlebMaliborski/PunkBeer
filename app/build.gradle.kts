import Dependencies.compose
import Dependencies.koin
import org.jetbrains.kotlin.ir.backend.js.compile

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

//apply<ExamplePlugin>()
//apply<GenerateClassPlugin>()

/*project.afterEvaluate {
  val task = tasks.named("transformDebugClassesWithAsm")
  println(task.get().inputs.properties.entries)
}*///"visitorsList.\$0.parameters.variantConfiguration"
dependencies {
  implementation(project(":base"))
  implementation(project(":navigation"))
  implementation(project(":feature-beer"))
  implementation("com.squareup.okhttp3:okhttp:4.9.3")
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.AndroidX.LIFECYCLE)
  implementation(Dependencies.Google.MATERIAL)

  compileOnly(fileTree(mapOf(
    "dir" to "/Users/gleb.maliborsky/StudioProjects/PunkBeer/app/libs",
    "include" to listOf("*.aar", "*.jar"),
  )))
  koin()
  compose()
  androidTestImplementation(Dependencies.Test.Integration.COMPOSE_UI)
  debugImplementation(Dependencies.Test.Integration.COMPOSE_TOOLING)
}
