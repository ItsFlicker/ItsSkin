import io.izzel.taboolib.gradle.*

plugins {
    `java-library`
    id("io.izzel.taboolib") version "2.0.12"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

taboolib {
    description {
        contributors {
            name("ItsFlicker")
        }
        dependencies {
            name("ItemsAdder")
//            name("Oraxen").optional(true)
//            name("PlaceholderAPI").optional(true)
//            name("Zaphkiel").optional(true)
        }
    }
    env {
        install(UNIVERSAL, BUKKIT_ALL)
    }
    version {
        taboolib = "6.1.2-beta9"
        coroutines = null
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.rosewooddev.io/repository/public/")
}

dependencies {
    compileOnly("com.github.LoneDev6:api-itemsadder:3.2.5")
//    compileOnly("com.github.oraxen:oraxen:-SNAPSHOT")
//    compileOnly("ink.ptms:Zaphkiel:2.0.14")

    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
//    compileOnly("ink.ptms:nms-all:1.0.0")

    compileOnly("com.google.code.gson:gson:2.9.0")
    compileOnly("com.google.guava:guava:31.1-jre")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    taboo(fileTree("libs-shaded"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}