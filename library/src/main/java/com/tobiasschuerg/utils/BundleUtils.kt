package com.tobiasschuerg.utils

import android.os.Bundle
import android.util.Log

/**
 * Prints all contents of the provided [Bundle] to the log.
 *
 * @param logger A lambda function for logging the contents of the bundle. Defaults to using
 * Android's Log.d with a default tag "[BundleDump]".
 */
fun Bundle.dumpBundle(logger: (String) -> Unit = { message -> Log.d("[BundleDump]", message) }) {
    logger("== Dumping Bundle - START ==")
    for (key in keySet()) {
        // There is no non-deprecated way to read a value of unknown type from a Bundle.
        @Suppress("DEPRECATION")
        val value = get(key)
        logger("[$key=$value]")
    }
    logger("== Dumping Bundle - END ==")
}
