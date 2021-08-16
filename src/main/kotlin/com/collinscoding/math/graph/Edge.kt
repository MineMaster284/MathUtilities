package com.collinscoding.math.graph

open class Edge<E: Any>(val from: E, val to: E) {
    override fun toString(): String = "$from -> $to"
}
class InfoEdge<A: Any>(from: A, to: A, val data: Map<String, String>): Edge<A>(from, to)

class EdgeSet<E: Any>(initialData: Set<Edge<E>> = setOf()): MutableSet<Edge<E>> by initialData.toMutableSet()
fun <E: Any> Collection<Edge<E>>.toEdgeSet() = EdgeSet(toSet())

class InfoEdgeSet<E: Any>(initialData: Set<InfoEdge<E>> = setOf()): MutableSet<InfoEdge<E>> by initialData.toMutableSet()
fun <E: Any> Collection<InfoEdge<E>>.toInfoEdgeSet() = InfoEdgeSet(toSet())
