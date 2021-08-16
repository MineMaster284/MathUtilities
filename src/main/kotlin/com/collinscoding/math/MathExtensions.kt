package com.collinscoding.math

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.ln

fun ((Double) -> Complex).fourier(frequency: Int, divisions: Int = 1000, window: (Double) -> Double = { t -> if (abs(t) < 1) 1.0 else 0.0 }): Complex =
    { t: Double ->
        this(t) * window(t) * Complex(cos(-2 * PI * frequency * t), sin(-2 * PI * frequency * t))
    }.integrate(0.0, 1.0, divisions) / divisions

fun Number.reciprocal() = 1 / toDouble()

fun cis(theta: Double) = Complex(cos(theta), sin(theta))

val Number.ln: Double
    get() = ln(toDouble())