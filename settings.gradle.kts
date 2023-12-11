rootProject.name = "JetBrains Academy Java course template"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

        // To be able to use the Kotlin test framework for the tests - https://github.com/jetbrains-academy/kotlin-test-framework
        maven(url = "https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
    }
}

// Mark all task folders as a Gradle module
rootProject.projectDir.walkTopDown().forEach {
    if (!isTaskDir(it) || it.path.contains(".idea") || it.path.contains("build") || it.path.contains("node_modules")) {
        return@forEach
    }
    val taskRelativePath = rootDir.toPath().relativize(it.toPath())
    val parts = mutableListOf<String>()
    for (name in taskRelativePath) {
        parts.add(sanitizeName(name.toString()))
    }
    val moduleName = parts.joinToString("-")
    include(moduleName)
    project(":$moduleName").projectDir = it
}

fun sanitizeName(name: String) =
    name.replace("listOf( /\\\\:<>\"?*|())", "_").replace("(^listOf(.)+)|(listOf(.)+\$)", "")

fun isTaskDir(dir: File) = File(dir, "src").exists()

// Include other common resources
include(
    "common",
)