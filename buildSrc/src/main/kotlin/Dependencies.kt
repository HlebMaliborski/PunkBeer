import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {

  object Koin {
    const val CORE = "io.insert-koin:koin-android:${Versions.KOIN}"
    const val ANDROID = "io.insert-koin:koin-android-compat:${Versions.KOIN}"
    const val COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
  }


  object Kotlin {
    const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
  }

  object AndroidX {
    const val CORE = "androidx.core:core-ktx:${Versions.AndroidX.CORE}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.KTX}"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.KTX}"
  }

  object Retrofit {
    const val CORE = "com.squareup.retrofit2:retrofit:2.7.2"
    const val GSON = "com.squareup.retrofit2:converter-gson:2.7.2"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.0"
    const val INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.0"
  }

  object Compose {
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.Compose.CORE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.Compose.CORE}"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.CORE}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.Compose.ACTIVITY}"
  }

  object Google {
    const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
  }

  object Test {
    object Unit {
      const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
    }

    object Integration {
      const val JUNIT = "androidx.test.ext:junit:${Versions.Test.JUNIT_INTEGRATION}"
      const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
      const val COMPOSE_UI = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.CORE}"
      const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.Compose.CORE}"
    }
  }

  fun DependencyHandlerScope.koin() {
    "implementation"(Koin.CORE)
    "implementation"(Koin.ANDROID)
    "implementation"(Koin.COMPOSE)
  }

  fun DependencyHandlerScope.retrofit() {
    "implementation"(Retrofit.CORE)
    "implementation"(Retrofit.GSON)
    "implementation"(Retrofit.OKHTTP)
    "implementation"(Retrofit.INTERCEPTOR)
  }

  fun DependencyHandlerScope.common() {
    "implementation"(Kotlin.STDLIB)
    "implementation"(AndroidX.CORE)
  }

  fun DependencyHandlerScope.commonIntegrationTest() {
    "testImplementation"(Test.Unit.JUNIT)
    "androidTestImplementation"(Test.Integration.JUNIT)
    "androidTestImplementation"(Test.Integration.ESPRESSO_CORE)
  }

}
