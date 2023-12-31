plugins {
    id("pure-kotlin-ios.app-conventions")
    id("pure-kotlin-ios.compose-conventions")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
    }
}
