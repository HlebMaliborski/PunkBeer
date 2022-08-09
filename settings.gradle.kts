import java.net.URI

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenLocal()
    //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/tools/android/gradle-plugin/")
    //maven (url = "https://repo.maven.apache.org/maven2/com/dynatrace/agent/agent-android/")
    maven {
      url = URI("https://repo.cuba-platform.com/content/groups/work")
    }
    google()
    //mavenLocal()
    mavenCentral()
  }
}
rootProject.name = "Punkbeer"
include(":app")
include(":feature-beer")
include(":base")
include(":navigation")
