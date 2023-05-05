import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

rootProject.name = "Kotlin Project Template"

include("domain")
"presentation".apply {
    includeModule("web")
    includeModule("acceptance-test")
}

"infrastructure".apply {
    includeModule("example-adapter")
}

fun String.includeModule(name: String) {
    val projectName = "$this:$name"
    include(":$projectName")
    project(":$projectName").projectDir = File("$this/${name.replace(':', '/')}")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    val kotlinVersion: String by settings
    val benManesVersion: String by settings
    val detektVersion: String by settings
    val dependencyCheckVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "com.github.ben-manes.versions" -> useVersion(benManesVersion)
                "io.gitlab.arturbosch.detekt" -> useVersion(detektVersion)
                "org.owasp.dependencycheck" -> useVersion(dependencyCheckVersion)
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
