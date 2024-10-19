dependencies {
    // testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.2")
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.14.2")
    testImplementation("org.awaitility:awaitility:4.2.2")
}

tasks.test {
    useJUnitPlatform()
}
