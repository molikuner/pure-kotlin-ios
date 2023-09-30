plugins {
    kotlin("multiplatform")
}

group = "com.molikuner"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    ios()
    iosSimulatorArm64()
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }

        all {
            languageSettings {
                optIn("kotlinx.cinterop.BetaInteropApi")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
    }
}
