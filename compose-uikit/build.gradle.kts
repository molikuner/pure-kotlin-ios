plugins {
    id("pure-kotlin-ios.app-conventions")
    id("pure-kotlin-ios.compose-conventions")
}

kotlin {
    sourceSets {
        iosMain {
            dependencies {
                implementation(project(":compose-ui-uikit"))
            }
        }
    }
}
