package com.tobiasschuerg.monads

/**
 * Created by Tobias Schürg on 13.03.2018.
 */

sealed class Either<out L, out R> {

    class Left<out L>(val value: L) : Either<L, Nothing>()
    class Right<out R>(val value: R) : Either<Nothing, R>()

    inline fun <L, R, T> Either<L, R>.fold(left: (L) -> T, right: (R) -> T): T =
            when (this) {
                is Left -> left(value)
                is Right -> right(value)
            }

    inline fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> =
            fold({ this as Left }, f)

    inline fun <L, R, T> Either<L, R>.map(f: (R) -> T): Either<L, T> =
            flatMap { Right(f(it)) }
}