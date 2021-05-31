import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc
import de.fayard.refreshVersions.migrateRefreshVersionsIfNeeded
buildscript {
    repositories { gradlePluginPortal()
        maven("https://dl.bintray.com/jmfayard/maven")
    }
    dependencies {
        classpath("de.fayard.refreshVersions:refreshVersions:0.9.5")
////                                             # available:0.9.6")
////                                             # available:0.9.7")
////                                             # available:0.10.0")
    }
}

migrateRefreshVersionsIfNeeded("0.9.5") // Will be automatically removed by refreshVersions when upgraded to the latest version.

bootstrapRefreshVersionsForBuildSrc()
