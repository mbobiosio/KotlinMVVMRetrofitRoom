# Kotlin-MVVM-Retrofit
This project is designed to express how to use Android MVVM architecture while exploiting the right practices.


![](https://github.com/mbobiosio/KotlinMVVMRetrofitRoom/blob/master/screenshots/mvvm-architecture.png)

## Technical Summary

- [Offline-First](https://applikeysolutions.com/blog/the-offline-first-approach-to-mobile-app-development): The offline-first apps, while still requiring a connection to the servers, don't need a constant internet connection. The data from servers is downloaded to the user's device and can still be accessed offline.
- [Single Source of Truth (SSOT)](https://developer.android.com/jetpack/docs/guide#truth): It is the practice of structuring information models and associated schemata such that every data element is stored exactly once. You can have an offline app and be sure your data always use one source and that is your database.
- [Model-View-ViewModel (MVVM)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel): It is a software architectural pattern that facilitates the separation of the development of the graphical user interface (without using [DataBinding](https://developer.android.com/topic/libraries/data-binding)). Also, there are [Screen States](/app/src/main/java/com/jaimegc/covid19tracker/ui/base/states/ScreenStates.kt) to handle the different states in the UI.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture): Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata): Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Stores UI-related data that isn't destroyed on UI changes.
  - [Room](https://developer.android.com/topic/libraries/architecture/room): The library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    - [DatabaseView](https://developer.android.com/training/data-storage/room/creating-views): This annotation allows you to encapsulate a query into a class. Room refers to these query-backed classes as views, and they behave the same as simple data objects when used in a DAO.
- [Retrofit 2](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter): A Retrofit 2 Converter.Factory for Kotlin serialization.
- [Detekt](https://github.com/detekt/detekt): A static code analysis tool for the Kotlin programming language. It operates on the abstract syntax tree provided by the Kotlin compiler.
- [Kotlin Gradle DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html): Gradle's Kotlin DSL provides an alternative syntax to the traditional Groovy DSL with an enhanced editing experience in supported IDEs, with superior content assist, refactoring, documentation, and more.
- [Remal check dependency update](https://plugins.gradle.org/plugin/name.remal.check-dependency-updates): Plugin that provides task for discovering dependency updates.
- [GitHub Actions](https://github.com/features/actions): Automate, customize, and execute your software development workflows right in your repository. Discover, create, and share actions to perform any job, including CI/CD, and combine actions in a completely customized workflow.
