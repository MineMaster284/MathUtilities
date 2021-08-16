package com.collinscoding.math

import com.collinscoding.utils.round
import kotlin.math.*

class Complex(var real: Double, var imaginary: Double): Number() {
    override fun toByte(): Byte = magnitude.toByte()
    override fun toChar(): Char = magnitude.toChar()
    override fun toDouble(): Double = magnitude
    override fun toFloat(): Float = magnitude.toFloat()
    override fun toInt(): Int = magnitude.toInt()
    override fun toLong(): Long = magnitude.toLong()
    override fun toShort(): Short = magnitude.toShort()

    override fun toString(): String = if (imaginary > 0) "($real + ${imaginary}i)" else "($real - ${abs(imaginary)}i)"
    val magnitude
        get() = sqrt(real.pow(2) + imaginary.pow(2))

    val heading
        get() = atan2(imaginary, real)

    val ln: Complex
        get() = Complex(ln(real), heading)

    val reciprocal
        get() = Complex(real / (real.pow(2) + imaginary.pow(2)), imaginary / (real.pow(2) + imaginary.pow(2)))

    val conjugate
        get() = Complex(real, -imaginary)

    operator fun plusAssign(n: Complex) {
        real += n.real
        imaginary += n.imaginary
    }
    operator fun plusAssign(n: Number) {
        plusAssign(Complex(n.toDouble(), 0.0))
    }

    operator fun plus(n: Complex) = Complex(real + n.real, imaginary + n.imaginary)
    operator fun plus(n: Number) = Complex(real + n.toDouble(), imaginary)

    operator fun minusAssign(n: Complex) {
        real -= n.real
        imaginary -= n.imaginary
    }
    operator fun minusAssign(n: Number) {
        minusAssign(Complex(n.toDouble(), 0.0))
    }

    operator fun minus(n: Complex) = Complex(real - n.real, imaginary - n.imaginary)
    operator fun minus(n: Number) = Complex(real - n.toDouble(), imaginary)

    operator fun timesAssign(n: Complex) {
        val final = times(n)
        real = final.real
        imaginary = final.imaginary
    }
    operator fun timesAssign(n: Number) {
        timesAssign(Complex(n.toDouble(), 0.0))
    }

    operator fun times(n: Complex) = Complex((real * n.real) - (imaginary * n.imaginary), (real * n.imaginary) + (imaginary * n.real))
    operator fun times(n: Number) = Complex(real * n.toDouble(), imaginary * n.toDouble())

    operator fun divAssign(n: Complex) {
        val final = div(n)
        real = final.real
        imaginary = final.imaginary
    }
    operator fun divAssign(n: Number) {
        divAssign(Complex(n.toDouble(), 0.0))
    }

    operator fun div(n: Complex) = times(n.reciprocal)
    operator fun div(n: Number) = Complex(real / n.toDouble(), imaginary / n.toDouble())

    operator fun unaryMinus() = Complex(-real, -imaginary)

    // e^(ln*exp)
    // e^R(ln*exp)*e^I(ln*exp)
    fun pow(exp: Number): Complex {
        val exponent = ln * exp
        val magnitude = E.pow(exponent.real)
        return Complex(magnitude * cos(exponent.imaginary), magnitude * sin(exponent.imaginary))
    }

    fun log(base: Number): Complex = this.ln / base.ln

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is Complex -> other is Number && imaginary == 0.0 && other.toDouble() == real
        else -> real == other.real && imaginary == other.imaginary
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + imaginary.hashCode()
        return result
    }

    fun round(places: Int): Complex = Complex(real.round(places), imaginary.round(places))

    companion object {
        fun fromAngle(theta: Double): Complex = Complex(cos(theta), sin(theta))
    }
}

fun ((Double) -> Complex).integrate(lowerBound: Double, upperBound: Double, divisions: Int = 500): Complex {
    val totalDelta = upperBound - lowerBound
    val sum = Complex(0.0, 0.0)
    (0..divisions)
        .map { it * totalDelta / divisions + lowerBound }
        .forEach { x ->
            sum += this(x)
        }
    return sum
}
fun ((Double) -> Number).integrate(lowerBound: Double, upperBound: Double, divisions: Int = 500): Double {
    val totalDelta = upperBound - lowerBound
    var sum = 0.0
    (0..divisions)
        .map { it * totalDelta / divisions + lowerBound }
        .forEach { x ->
            sum += this(x).toDouble()
        }
    return sum
}

fun ((Float) -> Complex).integrate(lowerBound: Float, upperBound: Float, divisions: Int = 500): Complex {
    val totalDelta = upperBound - lowerBound
    val sum = Complex(0.0, 0.0)
    (0..divisions)
        .map { it * totalDelta / divisions + lowerBound }
        .forEach { x ->
            sum += this(x)
        }
    return sum
}
fun ((Float) -> Number).integrate(lowerBound: Float, upperBound: Float, divisions: Int = 500): Float {
    val totalDelta = upperBound - lowerBound
    var sum = 0.0f
    (0..divisions)
        .map { it * totalDelta / divisions + lowerBound }
        .forEach { x ->
            sum += this(x).toFloat()
        }
    return sum
}
