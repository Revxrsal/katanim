import org.jreleaser.model.Active

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("org.jreleaser") version "1.13.1"
}

group = "manim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name

            from(components["java"])

            pom {
                name.set("katanim")
                description.set("A powerful, extendable, flexible yet simple to use commands annotation framework.")
                url.set("https://github.com/Revxrsal/katanim")
                inceptionYear.set("2024")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("Revxrsal")
                        name.set("Revxrsal")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/Revxrsal/katanim.git")
                    developerConnection.set("scm:git:ssh://github.com/Revxrsal/katanim.git")
                    url.set("https://github.com/Revxrsal/katanim")
                }
            }
        }
    }

    repositories {
        maven {
            url = layout.buildDirectory.dir("staging").get().asFile.toURI()
        }
    }
}
jreleaser {
    signing {
        active = Active.ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepositories.add("${rootProject.layout.buildDirectory}/staging")
                    stagingRepository("build/staging")
                }
            }
        }
    }
}