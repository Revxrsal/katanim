import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

subprojects {

    group = "io.github.revxrsal"
    version = "0.0.0.1"

    apply(plugin = "java")
    apply(plugin = "com.vanniktech.maven.publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    mavenPublishing {
        coordinates(
            groupId = group as String,
            artifactId = "katanim.$name",
            version = version as String
        )

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

        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

        signAllPublications()
    }
}