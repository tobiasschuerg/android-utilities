[![](https://jitpack.io/v/tobiasschuerg/android-utilities.svg)](https://jitpack.io/#tobiasschuerg/android-utilities)

# Android Utilities

This library offers a collection of Kotlin-based utilities that streamline common Android
development tasks. Utilizing mostly extension functions, it simplifies codebases across projects.

## Features

### Context Extensions (`com.tobiasschuerg.utils`)

- `Context.longToast(text: String)` / `Context.longToast(@StringRes text: Int)`: Displays a long-duration toast.
- `Context.isOnline()`: Checks whether the active network has validated internet access.
- `Context.onlineStatusFlow()`: Cold `Flow<Boolean>` emitting the connectivity status and every change of it,
  backed by a `ConnectivityManager.NetworkCallback`.
- `Context.isOnlineFlow(delayMillis: Long)`: Polling variant of `onlineStatusFlow()`.
- `Context.startActivity(activity: KClass<T>)`: Starts an activity without building the intent manually.
- `Activity.startActivityForResult(activity: KClass<T>, requestCode: Int)`
- `Activity.hideKeyboard()` / `Context.hideKeyboard(focusView: View)`: Hides the soft keyboard.

The connectivity helpers require the `android.permission.ACCESS_NETWORK_STATE` permission.

### Permissions (`PermissionUtil`)

- `Context.hasPermissions(vararg permissions: String)`: Checks whether all given permissions are granted.
- `Activity.requestPermissions(permissions: Array<String>)`: Requests permissions with a fixed request code.

### Bundle Extension

- `Bundle.dumpBundle(logger: (String) -> Unit = ...)`: Logs the contents of a `Bundle`, useful for debugging.

### View Extensions (`com.tobiasschuerg.utils.views`)

- `Button.performCheckedClick()`: Performs a click only if the button is clickable and enabled.
- `EditText.doOnDone(action: () -> Unit)`: Executes an action when the IME 'Done' action is triggered.

### Spannable DSL (`com.tobiasschuerg.spannable`)

```kotlin
val text = spannable {
    append(bold("Bold"))
    append(" and ")
    append(color(Color.RED, italic("red italic")))
}
```

Available spans: `bold`, `italic`, `underline`, `strike`, `sup`, `sub`, `size`, `color`, `background`, `url`.

### Date & Time (`com.tobiasschuerg.datetime`)

- `LocalDateRange(start, endInclusive)`: An iterable, inclusive range of `LocalDate`s.
- `LocalDate.atStartOfMonth()`, `atEndOfMonth()`, `atStartOfQuarter()`, `atEndOfQuarter()`,
  `atStartOfYear()`, `atEndOfYear()`
- `LocalDate.firstDayOfWeek()` / `lastDayOfWeek()`: Based on the default locale.
- `LocalDate.toUtcMillis()` / `LocalDateTime.toUtcMillis()`
- `LocalTime.totalMinutesSinceMidnight()`
- `TimeConstants`: Common time unit constants.

## Installation - Get it from JitPack

Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

Then add the dependency:

```kotlin
dependencies {
    implementation("com.github.tobiasschuerg:android-utilities:<version>")
}
```

Replace `<version>` with the latest release shown in the badge above, or a commit hash.

For details, visit [JitPack - Android Utilities](https://jitpack.io/#tobiasschuerg/android-utilities).

## Usage

To use the extension functions, simply call them from any valid context. For example, to hide the
keyboard from an activity:

```kotlin
hideKeyboard()
```

Or, to react to connectivity changes:

```kotlin
lifecycleScope.launch {
    onlineStatusFlow().collect { online ->
        // Handle online / offline state
    }
}
```

**Important Note:** This library uses `java.time` APIs and has a `minSdk` of 21. On devices below API 26 you must
enable core library desugaring in your app. For more information, see the
[official Android documentation](https://developer.android.com/studio/write/java8-support#library-desugaring).

## Contributing

Contributions are welcome! Run `./gradlew build` before opening a pull request; it runs lint, ktlint, detekt
and the unit tests.

## License

This library is available under the MIT license. See the LICENSE file for more info.
