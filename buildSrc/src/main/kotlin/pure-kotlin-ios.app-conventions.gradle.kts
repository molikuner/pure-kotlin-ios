plugins {
    id("pure-kotlin-ios.ios-conventions")
}

kotlin {
    iosArm64 {
        binaries {
            executable {
                entryPoint = "com.molikuner.kn.ios.main"
            }
        }
    }
    iosX64 {
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
}
