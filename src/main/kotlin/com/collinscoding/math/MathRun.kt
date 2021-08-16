package com.collinscoding.math

import com.collinscoding.math.matrix.Matrix
import com.collinscoding.math.matrix.Vector
import kotlin.math.PI

fun main() {
    val theta = PI / 2
    val v1 = Vector(5.0, 3.0)
    val dim = v1.dimensions.first
    val r = Matrix.rotationMatrix(dim, 0, 1, theta)
    println(r * (v1 * -2))
}