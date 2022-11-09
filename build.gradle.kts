// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.GRADLE}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    classpath("com.dynatrace.tools.android:gradle-plugin:8+")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

apply(plugin = "com.dynatrace.instrumentation")
configure<com.dynatrace.tools.android.dsl.DynatraceExtension> {
  strictMode(true)
  configurations {
    create("sampleConfig") {
      autoStart {
        applicationId("66cd19e7-8817-4cfa-b71e-16707f30cb8a")
        beaconUrl("https://bf04217tce.bf-dev.dynatracelabs.com/mbeacon")
      }
      userActions {

      }

      enabled(true)
      debug {
        agentLogging(true)
      }
      debug {
        byteCodeVerification(false)
      }
      userOptIn(false)
      agentBehavior.startupLoadBalancing(true)
      sessionReplay.enabled(false) // set to true if you want to participate in the preview
    }
  }
}

subprojects {
  pluginManager.withPlugin("com.android.library") {
    dependencies {
      "implementation"(com.dynatrace.tools.android.DynatracePlugin.agentDependency())
    }
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
