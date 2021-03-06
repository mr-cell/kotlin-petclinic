import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.gorylenko.gradle-git-properties") version "2.2.4"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

java.sourceCompatibility = JavaVersion.VERSION_15

allprojects {
    group = "mr.cell"
    version = "0.0.1-SNAPSHOT"
}

subprojects {
    ext {
        set("archUnitVersion", "0.15.0")
        set("mockitoKotlinVersion", "3.2.0")
    }

    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("com.gorylenko.gradle-git-properties")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "15"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

repositories {
    mavenCentral()
}

tasks.bootJar {
    enabled = false
}
