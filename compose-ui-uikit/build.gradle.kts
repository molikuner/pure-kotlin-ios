plugins {
    id("pure-kotlin-ios.compose-conventions")
}

kotlin {
    ios {
        withSourcesJar()
    }
    iosSimulatorArm64 {
        withSourcesJar()
    }
}
