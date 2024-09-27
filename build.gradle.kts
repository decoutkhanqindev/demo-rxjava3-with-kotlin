plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("io.reactivex.rxjava3:rxjava:3.1.9")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}