# LoadingDots Android Library

[![Maven Central](https://img.shields.io/maven-central/v/io.github.cna-mhmdi/loadingdots.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.cna-mhmdi/loadingdots/1.0.0)

`LoadingDots` is a customizable Android library that provides a loading indicator with animated dots. It's lightweight and easy to integrate into any Android project.

## Features

- **Customizable Dots**: You can adjust the number of dots, their color, and animation duration.
- **Constraint Layout Support**: It integrates seamlessly with constraint layouts, making it flexible for different UI needs.
- **Smooth Animations**: The animation of the dots is smooth and optimized for performance.

## Installation

#### Kotlin Gradle (build.gradle.kts)

Add the dependency in your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("io.github.cna-mhmdi:loadingdots:1.0.0")
}
```
#### Using lib-versions.toml
If you're using a lib-versions.toml file for dependency management, add the following:

```kotlin
[versions]
loading = "1.0.0"

[libraries]
loadingdots = { group = "io.github.cna-mhmdi", name = "loadingdots", version.ref = "loading" }
```

Then in your build.gradle.kts file:

```kotlin
dependencies {
    implementation(libs.loadingdots)
}

```

## Usage/Example
Add the LoadingDots view to your XML layout file. Here's a simple example:

```xml
<com.cna.loadingdots.LoadingDots
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:numCircles="5"
    app:animationDuration="1000"
    app:circleColor="@android:color/holo_purple"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"/>
```

## Attributes
`LoadingDots` comes with the following customizable attributes:

`app:numCircles`: Defines the number of circles in the animation. (Default: 3)

`app:animationDuration`: The duration of the animation in milliseconds. (Default: 500)

`app:circleColor:` The color of the dots. You can use a color resource or a direct color value. (Default: White)

## Use Case
If you need to display a loading indicator in your app, for example during network requests or processing tasks, LoadingDots provides a simple yet effective way to inform users that something is happening in the background. Its customizable nature makes it suitable for diverse design requirements.

## License
This library is licensed under the Apache 2.0 License.

                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/

            TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION

