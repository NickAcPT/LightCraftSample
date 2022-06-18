import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    java
    id("io.github.nickacpt.lightcraft.gradle") version "2.1.4-SNAPSHOT"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

lightcraft {
    clientVersion = ClientVersion.V1_8_9 // The client versions, check the enum for the ones that are available
    provideOptifineJarMod = true // Whether we should include optifine automatically, not all versions have optifine
    customMinecraftVersionName = "clientname_1.8.9" // version name for plugin folder (%userprofile%/.gradle/caches/lightcraft)

    launch {
        playerName = "ExampleName" // Player name to use in-dev - optional
        enableMixinsDebug = true // Whether to enable verbose Mixin debugging
    }
}