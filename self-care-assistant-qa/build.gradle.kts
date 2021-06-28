
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
}

group = "com.fls.self_care_assistant"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32")
    implementation("junit:junit:4.13.1")

    testImplementation("com.codeborne:selenide:5.20.4")
    testImplementation("io.github.bonigarcia:webdrivermanager:4.4.3")
    testImplementation("org.slf4j:slf4j-simple:1.7.30")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.platform:junit-platform-runner:1.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
}

tasks.test {
    useJUnitPlatform()
}
