# Kotlin multiplatform in action

The project consist of 3 components: 
- Android app.
- IOS app. 
- shared kotlin module containing the network layer that is being used in Android app and IOS app.

The shared module uses Ktor library for making api calls and data serialisation. 

## How ?
In `ApiEngine.kt` we only need to deal with the abstract class `io.ktor.client.HttpClient` which is annotated with `expect` without knowing any details about the concrete implementation.

The library generates the neccessary swift implementation in `iosMain` module and the proper kotlin implementation in `androidMain` with the same class name and method signatures
to used by respective client.

## What about UI ? 

Kotlin Multiplatform is intended to only cover the Model and view model layer and not the UI since each platform has its own standards and design guidelines.

There are few libraries that were somewhat successful in building shared UI components but they are in early stages of development (v 0.2.x)
