plugins {
    java
    `maven-publish`
    id("net.linguica.maven-settings") version "0.5"
}

group = "io.blockchallenge"
version = rootProject.version
java.targetCompatibility = JavaVersion.VERSION_16
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
    maven {
        name = "spigot-repo"
        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "github"
        setUrl("https://maven.pkg.github.com/blockchallenge-io")
    }
}

dependencies {
    compileOnly(project(":blockchallenge-base"))
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

publishing {
    repositories {
        maven {
            name = "github"
            setUrl("https://maven.pkg.github.com/blockchallenge-io/blockchallenge-lang")
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            groupId = group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}