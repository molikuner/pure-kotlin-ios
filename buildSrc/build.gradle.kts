plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    // maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20-Beta1")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.20-Beta1")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.6.11")
}
