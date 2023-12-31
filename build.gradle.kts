plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.6.0")
    implementation ("javax.xml.bind:jaxb-api:2.3.1")
    implementation ("org.glassfish.jaxb:jaxb-runtime:2.3.1")
    implementation ("xerces:xercesImpl:2.12.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}