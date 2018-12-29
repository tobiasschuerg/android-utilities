# Android Utilities

Provides utilities which I use in almost every project - now using Kotlin, mostly extension functions:

### extension functions on `Context`
- `isOnline()` quickly check for an internet connection.
- `longToast(message)` quickly toast something.
- `startActivity(Activity::class)` start an activity without explicitely creating a new intent etc.
- `startActivityForResult(Activity::class, code)` same as above but with a result code.

### extension function on `Bundle`
- `dumpBundle()` writes everything (keys and values) of the bundle to Log.d or a custom logger.
