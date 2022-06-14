plugins {
    id("java")
}

//plugins {
//    id 'java'
//    id 'idea'
//    id "io.qameta.allure" version "2.8.1"
//}

group = "ru.neji69"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//ext {
//    junitVersion = "5.2.0"
//    allureVersion = "2.13.5"
//    restAssuredVersion = "4.3.1"
//    slf4jVersion = "1.6.1"
//    aspectjVersion = "1.9.6"
//    lombokVersion = "1.18.10"
//    configVersion = "1.4.1"
//}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.36")

    testImplementation("io.rest-assured:rest-assured:4.4.0")
    testImplementation("com.google.code.gson:gson:2.9.0")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("com.codeborne:selenide:6.5.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}