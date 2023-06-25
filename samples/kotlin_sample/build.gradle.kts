import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "fur.token"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}

// What is used in gradle
kotlin {
    jvmToolchain(17)
}

// What is targeted
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("fur.token.libfurk.kotlinexample.KotlinSampleKt")
}

// Put the directly executable distribution under build/install
tasks.build {
    dependsOn("installDist")
}