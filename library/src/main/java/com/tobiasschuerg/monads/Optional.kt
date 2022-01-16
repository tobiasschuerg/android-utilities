package com.tobiasschuerg.monads

/**
 * Represents one optional value which can be there - or not.
 */
@Deprecated("since Kotlin supports nullable flows (instead of rx) this is not needed anymore")
sealed class Optional<out T> {

    abstract val value: T?

    val isPresent: Boolean
        get() = this is Some

    object None : Optional<Nothing>() {
        override val value: Nothing? = null
    }

    data class Some<out T> internal constructor(override val value: T) : Optional<T>()

    companion object {
        fun <T> ofNullable(value: T?): Optional<T> = when (value) {
            null -> Optional.None
            else -> Optional.Some(value)
        }

        fun <T> of(value: T): Optional<T> = Optional.Some(value)
    }
}
