dependencies {
    implementation(project(":core"))
    implementation(project(":http"))
    implementation(project(":db"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.bootJar {
    enabled = true
}

springBoot {
    buildInfo()
}
