pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
rootProject.name = "SubmissionCompose"
include(":app")
include(":core")
include(":common")
include(":data:movie")
include(":data:tvshow")
include(":feature:movie")
include(":domain")
include(":presenter")
include(":common:ui")
include(":feature:tvshow")
include(":data:favorite")
include(":feature:favorite")
include(":data:detail")
include(":feature:detail")
