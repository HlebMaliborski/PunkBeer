dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenLocal()
    google()
    mavenCentral()
  }
}
rootProject.name = "Punkbeer"
include(":app")
include(":feature-beer")
include(":base")
include(":navigation")
