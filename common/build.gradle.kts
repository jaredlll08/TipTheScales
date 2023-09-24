import com.blamejared.tipthescales.gradle.Versions
import com.blamejared.tipthescales.gradle.Properties
plugins {
    java
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
    id("com.blamejared.tipthescales.default")
}

minecraft {
    version(Versions.MINECRAFT)
    accessWideners(project.file("src/main/resources/${Properties.MODID}.accesswidener"))
    runs {
        client("Common Client") {
            workingDirectory(project.file("run"))
        }
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly("org.ow2.asm:asm-tree:9.2")
}