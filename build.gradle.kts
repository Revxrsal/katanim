import org.jreleaser.model.Active

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("org.jreleaser") version "1.13.1"
}

group = "io.github.revxrsal"
version = "1.0.0-SNAPSHOT"

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

            from(components["java"])
            groupId = "io.github.revxrsal"
            artifactId = "katanim"

            pom {
                name.set("Katanim")
                description.set("A mathematics animation library in Kotlin")
                inceptionYear.set("2024")
                url.set("https://github.com/Revxrsal/katanim/")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://mit-license.org/")
                        distribution.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("revxrsal")
                        name.set("Revxrsal")
                        url.set("https://github.com/Revxrsal/")
                    }
                }
                scm {
                    url.set("https://github.com/Revxrsal/katanim/")
                    connection.set("scm:git:git://github.com/Revxrsal/katanim.git")
                    developerConnection.set("scm:git:ssh://git@github.com/Revxrsal/katanim.git")
                }
            }
        }
    }
    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
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
            nexus2 {
                create("maven-central") {
                    active = Active.ALWAYS
                    url = "https://s01.oss.sonatype.org/service/local"
                    snapshotUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    closeRepository = true
                    releaseRepository = true
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}