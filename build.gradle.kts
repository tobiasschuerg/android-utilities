plugins {
    alias(libs.plugins.android.library) apply false
    // AGP 9 ships built-in Kotlin support; declaring KGP here pins the Kotlin version.
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)
    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)

    extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        buildUponDefaultConfig = true
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        parallel = true
    }
}
