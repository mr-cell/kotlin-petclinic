dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.hamcrest:hamcrest")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}