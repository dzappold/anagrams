import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions")
    id("kotlin-domain-conventions")
}

tasks {
    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            this.candidate.version.contains("alpha", ignoreCase = true) ||
                    this.candidate.version.contains("beta", ignoreCase = true) ||
                    this.candidate.version.contains("rc", ignoreCase = true) ||
                    this.candidate.version.contains("m", ignoreCase = true)
        }

        // optional parameters
        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

dependencies {
    api("dev.forkhandles:result4k")
}
