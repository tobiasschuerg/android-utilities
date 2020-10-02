package com.tobiasschuerg.utils

import android.os.Bundle
import android.util.Log

/**
 * Prints all contents of a [Bundle].
 */
@JvmOverloads
fun Bundle.dumpBundle(logger: (String) -> (Unit) = { it -> Log.d("[BundleDump]", it) }) {
    val it = keySet().iterator()
    logger(" == Dumping Intent - START ==")
    while (it.hasNext()) {
        val key = it.next()
        logger("[$key=${get(key)}]")
    }
    logger(" == Dumping Intent - END ==")
}
