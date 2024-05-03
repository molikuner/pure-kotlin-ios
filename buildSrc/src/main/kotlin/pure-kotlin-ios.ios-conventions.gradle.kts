plugins {
    kotlin("multiplatform")
}

group = "com.molikuner"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    // maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        all {
            languageSettings {
                optIn("kotlinx.cinterop.BetaInteropApi")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
    }
}
