package com.collinscoding.math.matrix

import kotlin.math.cos
import kotlin.math.sin

open class Matrix(val matrix: List<List<Double>>) {
    val dimensions = matrix.size to matrix[0].size
    constructor(dimensions: Pair<Int, Int>, vararg value: Double) : this(listOf(*(0 until dimensions.first).map { listOf(*value.slice(dimensions.second * it until (it + 1) * dimensions.second).toTypedArray()) }.toTypedArray()))

    override fun toString(): String = matrix.joinToString(separator = "\n") {
        it.joinToString(separator = " ") { value ->
            "%+.5f".format(value)
        }
    }

    operator fun plus(other: Matrix): Matrix {
        if (dimensions != other.dimensions) throw IllegalArgumentException("Matrices must be the same size")
        return Matrix(matrix.mapIndexed { rI, row -> row.mapIndexed { cI, col -> col + other.matrix[rI][cI] } })
    }

    operator fun times(scalar: Double): Matrix = Matrix(matrix.map { row -> row.map { col -> col * scalar } })
    operator fun times(scalar: Int): Matrix = Matrix(matrix.map { row -> row.map { col -> col * scalar } })

    operator fun times(other: Matrix): Matrix {
        if (dimensions.second != other.dimensions.first) {
            if (dimensions.first == other.dimensions.first) return transpose() * other
            if (dimensions.first != other.dimensions.second) throw IllegalArgumentException("Matrices must be m*n and n*p.")
            return other * this
        }
        return Matrix(
            dimensions.first to other.dimensions.second,
            *(0 until dimensions.first).flatMap { i ->
                (0 until other.dimensions.second).map { j ->
                    (0 until dimensions.second).sumByDouble { k ->
                        matrix[i][k] * other.matrix[k][j]
                    }
                }
            }.toDoubleArray()
        )
    }

    fun transpose(): Matrix = Matrix(dimensions.second to dimensions.first, *(0 until dimensions.second).flatMap { col -> (0 until dimensions.first).map { row -> matrix[row][col] } }.toDoubleArray())

    companion object {
        fun rotationMatrix(dimensions: Int, axis1: Int, axis2: Int, theta: Double): Matrix = Matrix((0 until dimensions).map { i ->
            (0 until dimensions).map { j ->
                when {
                    i == axis1 && j == axis1 -> cos(theta)
                    i == axis2 && j == axis2 -> cos(theta)
                    i == axis1 && j == axis2 -> -sin(theta)
                    i == axis2 && j == axis1 -> sin(theta)
                    i == j -> 1.0
                    else -> 0.0
                }
            }
        })
    }
}