# JetBrains Academy Java Course Template

[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Gradle Build](https://github.com/jetbrains-academy/java-course-template/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/jetbrains-academy/java-course-template/actions/workflows/gradle-build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> **Note**
>
> Click the <kbd>Use this template</kbd> button and clone it in IntelliJ IDEA.

**JetBrains Academy Java course template** is a repository that provides a
pure template to make it easier to create a new Java course with the [JetBrains Academy
plugin][ref:plugin.marketplace] (check out the [Creating a repository from a template][gh:template] article).

The main goal of this template is to speed up the setup phase
of Java course development for both new and experienced educators
by preconfiguring the project scaffold and CI,
linking to the proper documentation pages, and keeping everything organized.

If you're still not quite sure what this is all about, read our introduction: [What is the JetBrains Academy plugin?][docs:intro]

> **Note**
>
> Click the <kbd>Watch</kbd> button on the top to be notified about releases containing new features and fixes.

### Table of contents

In this README, we will highlight the following elements of template-project creation:

- [Getting started](#getting-started)
- [Gradle configuration](#gradle-configuration)
- [Course info configuration file](#course-info-configuration-file)
- [Course ignore file](#course-ignore-file)
- [Sample code](#sample-code)
- [Testing](#testing)
- [Predefined Run/Debug configurations](#predefined-rundebug-configurations)
- [Continuous integration](#continuous-integration)
- [Useful links](#useful-links)

## Getting started

Before we dive into course development and everything related to it, it's worth mentioning the benefits of using GitHub Templates.
By creating a new project with the current template, you start with no history or reference to this repository.
This allows you to create a new repository easily, without copying and pasting previous content, cloning repositories, or clearing the history manually.

All you need to do is click the <kbd>Use this template</kbd> button (you must be logged in with your GitHub account).

![Use this template][file:use-template-blur]

The most convenient way of getting your new project from GitHub is the <kbd>Get from VCS</kbd> action available on the Welcome Screen,
where you can filter your GitHub  repository by its name.

![Use this template][file:use-this-template.png]


As the last step, you need to manually review the configuration variables described in the [`gradle.properties`][file:gradle.properties] file and *optionally* move sources from the *org.jetbrains.academy.java.template* package to the one that works best for you.
Then you can get to work and implement your ideas.

## Gradle configuration

The recommended method for Java course development involves using the [Gradle][gradle] setup.

A course built using the JetBrains Academy Java course template includes a Gradle configuration already set up.
This gradle file sets up all base dependencies and plugins for the course.
For each gradle module (each task in the course and extra modules like `common` as well)
it includes [JUnit5][ref:junit5] tests.
It also marks the `source` and `test` folders as source- and test- source sets in the project.

### Gradle properties

The project-specific configuration file [`gradle.properties`][file:gradle.properties] contains:

| Property name       | Description                                                   |
|---------------------|---------------------------------------------------------------|
| `courseGroup`       | Package name.                                                 |
| `courseVersion`     | The current version of the course in [SemVer][semver] format. |
| `gradleVersion`     | Version of Gradle used for course development.                |
| `jvmVersion`        | Version of the JVM used for course development.               |

## Course template structure

A generated JetBrains Academy Java Course Template repository contains the following content structure:

```
.
├── .github/                    GitHub Actions workflows
├── .run/                       Predefined Run/Debug Configurations
├── build/                      Output build directory
├── gradle
│   └── wrapper/                Gradle Wrapper
├── common                      Course sources common for all sections
│   └── src
│       └── main
│           ├── java/           Java production sources
│           └── resources/      Resources - images, icons
├── courseSection/              An example of the course section 
│   ├── courseLesson/           An example of the course lesson
│   │   ├── theoryTask/         An example of a theory task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── task.md         Task/theory description
│   │   │   └── task-info.yaml  Task config file
│   │   ├── quizTask/           An example of a quiz task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── task.md         Task/quiz description
│   │   │   └── task-info.yaml  Task config file
│   │   ├── programmingTask/    An example of a programming task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── test/           Task tests
│   │   │   │   └── ...  
│   │   │   ├── task.md         Task description
│   │   │   └── task-info.yaml  Task config file
│   │   └── lesson-info.yaml    Lesson config file
│   ├── courseFrameworkLesson/  An example of the course framework lesson
│   │   ├── ...                 Several examples of lessons
│   │   └── lesson-info.yaml    Lesson config file
│   └── section-info.yaml       Section config file
├── .courseignore               Course ignoring rules
├── .gitignore                  Git ignoring rules
├── build.gradle.kts            Gradle configuration
├── course-info.yaml            Course info configuration file
├── gradle.properties           Gradle configuration properties
├── gradlew                     *nix Gradle Wrapper script
├── gradlew.bat                 Windows Gradle Wrapper script
├── LICENSE                     License, MIT by default
├── README.md                   README
└── settings.gradle.kts         Gradle project settings
```

## Course info configuration file

The course info configuration file is the [course-info.yaml][file:course-info.yaml] file located in the root directory.
It provides general information about the course, like description, language, etc.

```yaml
type: marketplace
title: JetBrains Academy Java course template
language: English
summary: Course description
programming_language: Java
content:
  - courseSection
environment_settings:
  jvm_language_level: JDK_17
```

## Course ignore file

The course ignore file is the [.courseignore][file:courseignore] file located in the root directory.
It provides the list of files or directories that will be ignored in the final course preview or archive.

```text
README.md
/.run
```

You can find more information about the course preview in the [Course preview][ref:course.preview] section. Information
about creating a course archive and uploading it to the marketplace is in the [Course distribution][ref:course.distribution] section.

## Sample code

The prepared template provides an example of a course with one section, two lessons, and five tasks in total.

![Course structure in the course creator mode][file:course-structure-author]

Each course may have an unlimited number of sections, lessons, and tasks.
Students will see almost the same course structure as the educator (course author):

![Course structure in the course student mode][file:course-structure-student]

The main difference is in framework lessons, which display
only task files, without intermediate steps.

You can read more about framework lessons in the official documentation in the [Framework Lessons Creation][ref:framework.lessons.creation] section.

> **Note**
>
> Click <kbd>Course Creator</kbd> -> <kbd>Create Course Preview</kbd> in the context menu in the root of the repository to create a course preview.


The JetBrains Academy plugin provides five different types of tasks,
and you can combine them inside one lesson (whether regular or framework).
You can read more about tasks in the official documentation in the [Task][ref:tasks] section.

## Testing

To check the programming exercises for [**edu**][ref:tasks] tasks, you need to write tests.
It contains functionality to test student solutions by using the [Java Reflection API][ref:java.reflection.api] under the hood.
This approach allows you to call students' functions that do not exist yet.
It is a powerful mechanism that enables you to create excesses without predefined classes or function
templates and check their signature and behaviour properly.

You can find little examples of programming tasks in the repository in the `Tests.java` files:
in [course lesson][file:course.lesson.tests] and [course framework lesson][file:course.framework.lesson.tests].

## Predefined Run/Debug configurations

Within the default project structure, there is a `.run` directory provided, which contains predefined *Run/Debug configurations* that expose corresponding Gradle tasks:

![Run/Debug configurations][file:run-debug-configurations]

| Configuration name       | Description                                                                    |
|--------------------------|--------------------------------------------------------------------------------|
| Build course             | Runs `:build` Gradle task with tests only.                                     |

## Continuous integration

Continuous integration depends on [GitHub Actions][gh:actions], a set of workflows that make it possible to automate your testing and release process.
Thanks to such automation, you can delegate the testing and verification phases to the Continuous Integration (CI) and instead focus on development (and writing more tests).

In the `.github/workflows` directory, you can find definitions for the following GitHub Actions workflows:
- [Build](.github/workflows/gradle-build.yml)
    - Builds your course
    - Runs all tests for all tasks

## Useful links

- [JetBrains Academy plugin][ref:plugin.marketplace]
- [Course creator start guide][ref:course.creator.start.guide]
- [Courses on Marketplace][ref:marketplace]

[gh:actions]: https://help.github.com/en/actions
[gh:template]: https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template

[ref:marketplace]: https://plugins.jetbrains.com/education
[ref:course.creator.start.guide]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html
[ref:plugin.marketplace]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy
[ref:course.preview]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html#preview_course
[ref:course.distribution]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html#course_distribution
[ref:framework.lessons.creation]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/framework-lessons-guide-for-course-creators.html#a81e8983
[ref:tasks]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/framework-lessons-guide-for-course-creators.html#a81e8983
[ref:java.reflection.api]: https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/index.html
[ref:junit5]: https://junit.org/junit5/

[docs:intro]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/jetbrains-academy-plugin-faq.html#what_is_the_jetbrains_academy_plugin

[file:gradle.properties]: ./gradle.properties
[file:course-info.yaml]: ./course-info.yaml
[file:courseignore]: .courseignore
[file:course.lesson.tests]: ./courseSection/courseLesson/programmingTask/test/Tests.java
[file:course.framework.lesson.tests]: ./courseSection/courseFrameworkLesson/programmingTask/test/Tests.java

[gradle]: https://gradle.org

[semver]: https://semver.org

[file:use-this-template.png]: common/src/main/resources/images/get-from-version-control.png
[file:course-structure-author]: common/src/main/resources/images/course-structure-author.png
[file:course-structure-student]: common/src/main/resources/images/course-structure-student.png
[file:run-debug-configurations]: common/src/main/resources/images/run-debug-configurations.png
[file:use-template-blur]: common/src/main/resources/images/use_template_blur.jpg
