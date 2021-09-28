import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    java
    id("io.github.nickacpt.lightcraft.gradle") version "1.3.1-SNAPSHOT"
}

group = "io.github.nickacpt.lightcraft.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

lightcraft {
    clientVersion = ClientVersion.V1_7_10
    provideOptifineJarMod = false
}