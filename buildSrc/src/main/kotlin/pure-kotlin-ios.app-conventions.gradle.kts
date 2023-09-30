plugins {
    id("pure-kotlin-ios.ios-conventions")
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
}
