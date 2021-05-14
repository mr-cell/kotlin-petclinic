dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.hamcrest:hamcrest")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}