import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    `maven-publish`
    `java-library`

    id("org.jetbrains.kotlinx.kover") version "0.7.1"
}

group = "fur.token"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${buildDir}/repo")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

// What is used in gradle
kotlin {
    jvmToolchain(17)
}

// What is targeted
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.named("build").configure {
    finalizedBy("publish")
}
