rootProject.name = "pure-kotlin-ios"

include("direct-uikit")
include("compose-jb")
include("compose-uikit")
include("compose-ui-uikit")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
