plugins {
    id("org.jetbrains.compose")
    id("pure-kotlin-ios.ios-conventions")
    id("org.jetbrains.kotlin.plugin.compose")
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
