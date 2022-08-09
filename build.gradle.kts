// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/tools/android/gradle-plugin/")
    //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/agent/agent-android/")
    mavenLocal()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.GRADLE}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    classpath("com.dynatrace.tools.android:gradle-plugin:8.252.0.1001")
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

/*gradle.taskGraph.afterTask {
  println("--------------------------------------------------------------------------------")
  println(" Task '${project.name}:${this.name}'")
  println("--------------------------------------------------------------------------------")
  println("")

  println("File inputs:")
  this.inputs.files.forEach { _ ->
    //println(" - $it")
  }
  println("")

  println("Property inputs:")
  this.inputs.properties.forEach {
    println(" - $it")
  }
  println("")

  println("--------------------------------------------------------------------------------")
  println("")
}*/
var a = 0
gradle.afterProject {
  println("Evaluation of $a++")

}

tasks.register("hello") {
  group="habr"
  description="Simple hello world task"
  //dependsOn(":app:transformDebugClassesWithAsm")
  doLast {
    println("Hello world!")
  }
}

project.afterEvaluate {
  //println(tasks.named(":app:transformDebugClassesWithAsm").name)
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
