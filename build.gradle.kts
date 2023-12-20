import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun gradleProperties(key: String) = providers.gradleProperty(key)

group = gradleProperties("courseGroup").get()
version = gradleProperties("courseVersion").get()

plugins {
    java
    val kotlinVersion = "1.9.0"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            // To be able to use the Kotlin test framework for the tests - https://github.com/jetbrains-academy/kotlin-test-framework
            url = uri("https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
        }
    }
}

tasks {
    wrapper {
        gradleVersion = gradleProperties("gradleVersion").get()
    }
}

// Configure dependencies for all gradle modules
configure(subprojects) {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    // Include dependencies
    dependencies {
        // By default, only the core module is included
        implementation("org.jetbrains.academy.test.system:core:2.1.2")

        val junitJupiterVersion = "5.9.0"
        implementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        implementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
        runtimeOnly("org.junit.platform:junit-platform-console:1.9.0")
    }

    val jvmVersion = gradleProperties("jvmVersion").get()
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = jvmVersion
            }
        }
        withType<JavaCompile> {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }

        // This part is necessary for the JetBrains Academy plugin
        withType<Test> {
            useJUnitPlatform()

            outputs.upToDateWhen { false }

            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.split("\n")
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }
}

// We have to store tests inside test folder directly
configure(subprojects.filter { it.name != "common" }) {
    sourceSets {
        getByName("main").java.srcDirs("src")
        getByName("test").java.srcDirs("test")
    }

    dependencies {
        implementation(project(":common"))
    }

    tasks.register<Exec>("run") {
        // Just do nothing to avoid the edu plugin errors
    }
}
