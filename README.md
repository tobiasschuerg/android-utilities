[![](https://jitpack.io/v/tobiasschuerg/android-utilities.svg)](https://jitpack.io/#tobiasschuerg/android-utilities)

# Android Utilities

This library offers a collection of Kotlin-based utilities that streamline common Android development tasks. Utilizing mostly extension functions, it simplifies codebases across projects.

## Features

### Context Extensions
- `hideKeyboard()`: Hides the soft keyboard if present.
- `isOnline()`: Checks for an internet connection.
- `longToast(message: String)`: Displays a long-duration toast message.
- `longToast(@StringRes messageId: Int)`: Displays a long-duration toast with a string resource.
- `startActivity(activityClass: KClass<out Activity>)`: Starts an activity with simplified intent creation.
- `requestPermissions(permissions: Array<String>)`: Requests runtime permissions without a result code for streamlined permission handling.

### Bundle Extension
- `dumpBundle(logger: (String) -> Unit = Log::d)`: Logs the contents of a `Bundle`, useful for debugging.

### Button Extension
- `performCheckedClick()`: Performs a click action if the button is clickable and enabled.

### EditText Extension (Assuming it's part of the utilities)
- `doOnDone(action: () -> Unit)`: Executes an action when the 'Done' option is selected on the keyboard.

## Installation - Get it from JitPack

Add the library to your project via JitPack:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.tobiasschuerg:android-utilities:latest.version.here'
}
```
Replace latest.version.here with the latest version number or commit hash to ensure you're using the most up-to-date release.

For the latest version and details about how to include it in your project, visit:

[JitPack - Android Utilities](https://jitpack.io/#tobiasschuerg/android-utilities)

## Usage
To use the extension functions, simply call them from any valid context. For example, to hide the keyboard from an activity:
```kotlin
hideKeyboard()
```
Or, to check for an internet connection:

```kotlin
if (isOnline()) {
    // Handle online state
} else {
    // Handle offline state
}
```
For more detailed examples and advanced usage, refer to the comments in the source code.

## Contributing
Contributions are welcome!

## License
This library is available under the MIT license. See the LICENSE file for more info.
