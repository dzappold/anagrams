import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

rootProject.name = "anagrams"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    val kotlinVersion: String by settings
    val benManesVersion: String by settings
    val detektVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "com.github.ben-manes.versions" -> useVersion(benManesVersion)
                "io.gitlab.arturbosch.detekt" -> useVersion(detektVersion)
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}
