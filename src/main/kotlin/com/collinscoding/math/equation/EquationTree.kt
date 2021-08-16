package com.collinscoding.math.equation

import com.collinscoding.math.Complex

class EquationTree(val equation: String) {
    init {

    }
}

class ComputationalNode(val sign: MathFunction)

enum class MathFunction(effect: (Complex, Complex) -> Complex) {
    PLUS({a, b -> a + b}), MINUS({a, b -> a - b}), MULT({a, b -> a * b}), DIV({a, b -> a / b}), POW({a, b -> a.pow(b)})
}