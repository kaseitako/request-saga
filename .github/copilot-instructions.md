# Copilot Coding Agent Onboarding Guide

## 使用言語
日本語でレビューしてください。

## High-Level Repository Overview

- **Repository Summary**:  
  `request-saga` appears to be a minimal Java project, likely an application skeleton or starting point for development. The presence of only a `Main.java` file suggests it may serve as a template or base for further expansion.

- **Size & Project Type**:  
  The repository is small and contains only essential project, build, and wrapper files.  
  **Type**: Java 25+ project (Gradle-based, using Kotlin DSL).

- **Languages & Tooling**:  
  - Primary Language: Java (main class in `src/main/java`)
  - Build Tool: Gradle (Kotlin DSL syntax, see `build.gradle.kts`)
  - Lint/Format: [Spotless](https://github.com/diffplug/spotless) plugin is intended or present for code formatting (see PR #1)
  - Test Framework: JUnit 5 (via dependencies in build file)

---

## Build Instructions

All build/test/lint commands should be run from the project root. Use the Gradle wrapper (`./gradlew` for Unix/macOS, `gradlew.bat` for Windows), not a globally-installed Gradle.

### 1. Bootstrap

- No explicit bootstrap is required; dependencies are handled via Gradle.

### 2. Build

```sh
./gradlew build
```
- This command will:
  - Download and initialize the Gradle wrapper (Gradle 9.2.1) as specified in `gradle/wrapper/gradle-wrapper.properties`.
  - Pull all Java dependencies.
  - Compile code and run tests.
  - Perform code formatting checks if Spotless is configured.

**Always run `./gradlew build` after pulling new changes or modifying dependencies.**

### 3. Test

```sh
./gradlew test
```
- Runs JUnit 5 tests in the project using the JUnit Platform.

### 4. Lint/Format

If the `Spotless` plugin is configured (see build file), run:

```sh
./gradlew spotlessCheck      # Check code format
./gradlew spotlessApply      # Automatically reformat code to match style
```
- **Always run `spotlessCheck` before submitting changes.**
- To autocorrect formatting, use `spotlessApply`.

### 5. Clean

```sh
./gradlew clean
```
- Cleans build outputs and prepared environment.

### 6. Run/Execute

If the project adds an application entrypoint, use:
```sh
./gradlew run
```
- By default, the minimal skeleton may not have a proper main entrypoint.

### Known Issues / Workarounds

- Ensure you are using at least Java 21 (Java 25 is set in `build.gradle.kts`). Use SDKMAN or other tools to manage JDK.
- If Spotless is not installed, a build error will occur for `spotless*` tasks. Add its plugin in `build.gradle.kts` as needed.
- If dependencies do not download, check your network and Gradle proxy settings.

---

## Project Layout & Architecture

**Key Directories and Files**:
- `build.gradle.kts`: Gradle build config (Kotlin DSL)  
- `settings.gradle.kts`: Additional Gradle settings  
- `gradle/wrapper/gradle-wrapper.properties`: Specifies gradle version
- `src/main/java/dev/kaseitako/Main.java`: (Sample main class)

**Standard Directory Structure**:
```
/ (repo root)
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── src/
│   └── main/
│       └── java/
│           └── dev/
│               └── kaseitako/
│                   └── Main.java
├── .gitignore
├── .gitattributes
```

- **CI/CD**: As of this writing, no GitHub Actions workflows or other pipelines detected in `.github`. Add one if automation of build/test/lint is desired before merging PRs.

**Major Configurations**:
- Linting/formatting and most build configuration are exclusively through the `build.gradle.kts` using Gradle plugins.
- Add custom tasks in `build.gradle.kts` as needed for more steps.

---

## Recommended Coding Agent Practices

- **Trust the instructions above. Only explore the codebase if new files or requirements are found, or errors are encountered.**
- **Favor the provided Gradle wrapper for all commands.**
- Always check/test your code locally with both `./gradlew build` and `./gradlew spotlessCheck` before proposing changes.
- All new code should reside under `src/main/java` in the appropriate package, unless otherwise required.
- If a test directory exists or is created (`src/test/java`), put test code there and run tests as described above.
- For code formatting/lint: Always validate with `spotlessCheck` before submitting.
- For continuous integration, suggest use of a simple GitHub Actions workflow (if missing) to automate build/test/lint.
- If documentation or architectural changes are made, suggest updating/creating a `README.md` or similar documentation in project root.
- If project does not build due to missing tools or plugins, ensure versions in build and wrapper files are correct and dependencies declared.

---

## File Inventory (root and main dirs)

- Root files:  
  `.gitattributes`, `.gitignore`, `build.gradle.kts`, `settings.gradle.kts`, `gradlew`, `gradlew.bat`
- Gradle wrapper:  
  `gradle/wrapper/gradle-wrapper.properties`
- Source:  
  `src/main/java/dev/kaseitako/Main.java`
- Other directories:  
  `.idea` (IDE config), no effect on build/test

**No README.md or workflows currently present as of this inventory.**

---

## Final Guidance

Refer to the above instructions for all basic build, test, and lint actions. If the instructions ever appear to be out-of-date (e.g., a file or task referenced does not exist, or build fails for unknown reasons), then and only then perform a limited search for missing files or updated steps.

**Do not waste cycles searching for standard files or commands already documented above.**

---

*This onboarding guide is limited to two pages for maximum agent efficiency.*