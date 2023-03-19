import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.blamejared.com") {
        name = "BlameJared"
    }
}

dependencies {
    gradleApi()
    implementation(group = "com.blamejared", name = "ModTemplate", version = "3.0.0.38")
    implementation(group = "net.darkhax.curseforgegradle", name = "CurseForgeGradle", version = "1.0.10")
}

gradlePlugin {
    plugins {
        create("default") {
            id = "com.blamejared.tipthescales.default"
            implementationClass = "com.blamejared.tipthescales.gradle.DefaultPlugin"
        }
        create("loader") {
            id = "com.blamejared.tipthescales.loader"
            implementationClass = "com.blamejared.tipthescales.gradle.LoaderPlugin"
        }
    }
}
