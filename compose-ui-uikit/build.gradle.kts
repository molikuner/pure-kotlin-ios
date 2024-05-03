plugins {
    id("pure-kotlin-ios.compose-conventions")
}

kotlin {
    iosArm64 {
        withSourcesJar()
    }
    iosX64 {
        withSourcesJar()
    }
    iosSimulatorArm64 {
        withSourcesJar()
    }
}
