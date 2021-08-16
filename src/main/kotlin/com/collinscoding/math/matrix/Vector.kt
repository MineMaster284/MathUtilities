package com.collinscoding.math.matrix

class Vector(matrix: Matrix): Matrix(matrix.matrix) {
    constructor(vararg value: Double) : this(Matrix(value.size to 1, *value))
}