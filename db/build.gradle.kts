dependencies {
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
