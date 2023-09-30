plugins {
    id("org.jetbrains.compose")
    id("pure-kotlin-ios.ios-conventions")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
            }
        }
    }
}
