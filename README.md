Timeline
=====

An open-source Android app for showing all post on timeline with bookmarking favourite posts.

Introduction:
=====

> Timeline is the materialization of how (I think)  the Architecture of a good Android Application should be

Timeline is my sandbox project, where test my ideas, ideal architectures and play around with libraries. <br>It's pretty close to a full-fledged application, complete with Network Requests, Local DB and Material Design.

It contains some bells and whistles like **Kotlin**, **Coroutines**, **Jetpack** & **AndroidX**.

Screenshots & Gifs:
=====
<p>
  <img src="https://i.imgur.com/vdgfk9F.jpg" width="250" style="margin:2px"/>
  <img src="https://i.imgur.com/s5c3dNz.jpg" width="250" style="margin:2px"/>
  <img src="https://i.imgur.com/JllJk0V.jpg" width="250" style="margin:2px"/>
</p>

Downloads:
=====

Download the latest APK [here](https://github.com/pankajdgeek/timeline/raw/master/app/release/app-release.apk)


Design Decisions & Dependencies:
=====

### [Kotlin](https://kotlinlang.org/):

I :heart: Kotlin. It's a breath of fresh air when coming from Java, and makes code so much nicer to read and write. It has OOTB support for Lambdas, Extension Functions, DSLs and a vast stdlib. JetBrains actively maintains and releases stable versions every month. All my projects (including this one) will be 100% Kotlin.

### [JetPack](https://developer.android.com/jetpack) - Architecture Components & AndroidX:
Would be a loss to build an application without these libraries. With Google advocating MVVM, and these libraries working so flawlessly with each other, it really leaves you no choice.
<br>**Room** - Database Layer
<br>**ViewModel** - Data Preservation across configuration changes
<br>**Lifecycle** - Handling annoying issues with Activities / Fragments namely when pushing data during false states
<br>**Navigation** - Handling Intent / Fragment Transactions, isolating sources from destinations and easy argument passing!
<br>**AndroidX, Material Components** - For embracing Material Design and backporting API features to minSdk

### [Koin](https://insert-koin.io/) - Dependency Injection:
Sick and tired of Dagger in Production, and annoyed by it slowing down my build, I turned to a substitute. [Kodein](https://github.com/Kodein-Framework/Kodein-DI) seemed to be the recommended (and a more established) library for Android, however I chose Koin for it's sheer simplicity. It also made strides in performance in v2.0, which makes it my current choice for DI without code generation.

### [Gson](https://github.com/google/gson) + [Retrofit](https://square.github.io/retrofit/) - Networking:
A very simple choice when it comes to using REST APIs.

### Other Gems

- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Dependency Updates Plugin](https://github.com/ben-manes/gradle-versions-plugin)

# Contributions:

Contributions in any form are welcome.
<br>Just make sure to have fun and learn new things :)
