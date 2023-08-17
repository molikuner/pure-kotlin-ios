plugins {
    kotlin("multiplatform") version "1.9.0"
}

group = "com.molikuner"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    ios {
        binaries {
            executable {
                entryPoint = "com.molikuner.kn.ios.main"
            }
        }
    }
    iosSimulatorArm64 {
        binaries {
            executable {
                entryPoint = "com.molikuner.kn.ios.main"
            }
        }
    }
    sourceSets {
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
