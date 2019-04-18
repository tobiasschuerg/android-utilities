[![](https://jitpack.io/v/tobiasschuerg/android-utilities.svg)](https://jitpack.io/#tobiasschuerg/android-utilities)


# Android Utilities

Provides basic utilities which I use in almost every project - now using Kotlin, mostly extension functions:

### extension functions on `Context`
- `hideKeyboard()` hides the soft keyboard.
- `isOnline()` quickly check for an internet connection.
- `longToast(message)` quickly toast something.
- `startActivity(Activity::class)` start an activity without explicitely creating a new intent etc.
- `startActivityForResult(Activity::class, code)` same as above but with a result code.

### extension function on `Bundle`
- `dumpBundle()` writes everything (keys and values) of the bundle to Log.d or a custom logger.

### extension function on `Button`
- `performCheckedClick()` programmatically perform a button click, only off the button is actually clickable.

### extension function on `EditText`
- `doOnDone()` trigger an event if the 'done'-action is invoked.


## Get it from Jitpack:
https://jitpack.io/#tobiasschuerg/android-utilities/
```
implementation 'com.github.tobiasschuerg:android-utilities:+'
```
