package com.collinscoding.math

import org.junit.Assert.assertEquals
import org.junit.Test

class MathExtensionsTest {
    @Test
    fun testIntegrate() {
        assertEquals(Complex(5.0, 1 / 2.0 + 1 / 3.0), { t: Double -> Complex(t, 1 / t) }.integrate(2.0, 3.0, 1))
        assertEquals(5.0, { t: Double -> t }.integrate(2.0, 3.0, 1), 0.05)
        assertEquals(Complex(5.0, 0.0), { t: Float -> Complex(t.toDouble(), 0.0) }.integrate(2.0f, 3.0f, 1))
        assertEquals(5.0f, { t: Float -> t }.integrate(2.0f, 3.0f, 1))
        assertEquals(Complex(5.0, 2.0).reciprocal(), Complex(5.0, 2.0).pow(-1))
    }
}