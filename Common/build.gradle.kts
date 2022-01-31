import com.blamejared.modtemplate.Utils

plugins {
    java
    `maven-publish`
    id("com.blamejared.modtemplate")
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
}

val minecraftVersion: String by project
val modName: String by project
val modId: String by project
val modVersion: String by project

val baseArchiveName = "${modName}-common-${minecraftVersion}"
version = Utils.updatingVersion(modVersion)

base {
    archivesName.set(baseArchiveName)
}

minecraft {
    version(minecraftVersion)
    runs {
        client("vanilla_client") {
            workingDirectory(project.file("run"))
        }
        server("vanilla_server") {
            workingDirectory(project.file("run"))
        }
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly("org.ow2.asm:asm-tree:9.2")
}

tasks.processResources {

    val buildProps = project.properties

    filesMatching("pack.mcmeta") {

        expand(buildProps)
    }
}
publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            artifactId = baseArchiveName
            from(components["java"])
        }
    }

    repositories {
        maven("file://${System.getenv("local_maven")}")
    }
}