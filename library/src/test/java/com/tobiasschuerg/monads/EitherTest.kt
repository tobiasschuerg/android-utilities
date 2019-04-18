package com.tobiasschuerg.monads

import org.junit.Assert.assertEquals
import org.junit.Test

class EitherTest {

    @Test
    fun `test that getLeftValue returns the correct value`() {
        val either = Either.Left(42)
        assertEquals(42, either.getLeftValue())
    }

    @Test(expected = NullPointerException::class)
    fun `test that getLeftValue throws if right is an exception`() {
        val either = Either.Right(NullPointerException("foo bar"))
        either.getLeftValue()
    }

    @Test
    fun `test that getRightValue returns the correct value`() {
        val either = Either.Right(1337)
        assertEquals(1337, either.getRightValue())
    }

    @Test(expected = NoSuchElementException::class)
    fun `test that getRightValue throws if left is an exception`() {
        val either = Either.Left(NoSuchElementException("foo bar"))
        either.getRightValue()
    }
}